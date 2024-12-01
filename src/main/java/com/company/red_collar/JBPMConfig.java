package com.company.red_collar;

import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.event.process.DefaultProcessEventListener;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkflowProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

@Configuration
public class JBPMConfig {
    private static final Logger logger = LoggerFactory.getLogger(JBPMConfig.class);

    @Autowired
    private WriteApiBlocking writeApiBlocking;

    @Bean
    public KieContainer kieContainer() {
        logger.info("Initializing KIE Container");
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        if (kieContainer != null) {
            logger.info("KIE Container initialized successfully");
        } else {
            logger.error("KIE Container initialization failed");
        }
        return kieContainer;
    }

    @Bean
    public KieSession kieSession(KieContainer kieContainer) {
        logger.info("Creating KIE Session");
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");

        kieSession.addEventListener(new DefaultProcessEventListener() {
            @Override
            public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
                String nodeName = event.getNodeInstance().getNodeName();
                ProcessInstance processInstance = event.getProcessInstance(); 
                if (processInstance != null && processInstance instanceof WorkflowProcessInstance) {
                    WorkflowProcessInstance workflowProcessInstance = (WorkflowProcessInstance) processInstance;

                    String firstName = (String) workflowProcessInstance.getVariable("firstName");
                    String lastName = (String) workflowProcessInstance.getVariable("lastName");
                    String itemType = (String) workflowProcessInstance.getVariable("itemType");
                    String itemSize = (String) workflowProcessInstance.getVariable("itemSize");
                    String itemColor = (String) workflowProcessInstance.getVariable("itemColor");
                    Integer itemIndex = (Integer) workflowProcessInstance.getVariable("itemIndex");
                    // Write to InfluxDB
                    //writeOrderToInfluxDB(firstName, lastName, itemType, itemSize, itemColor, itemIndex, 0, nodeName);
                }

                // Log node transition
                if (!nodeName.equals("End")) {
                    System.out.print(nodeName + " --> ");
                } else {
                    System.out.println(nodeName);
                }
            }
        });

        return kieSession;
    }

    // Write data to InfluxDB
    private void writeOrderToInfluxDB(String firstName, String lastName, String itemType, String itemSize, String itemColor, Integer itemIndex, Integer totalQuantity, String nodeName) {
        Point point = Point.measurement("order_measurement")
                .addField("first_name", firstName)
                .addField("last_name", lastName)
                .addField("item_type", itemType)
                .addField("item_size", itemSize)
                .addField("item_color", itemColor)
                .addField("item_index", itemIndex)
                .addField("total_quantity", totalQuantity)
                .addField("item_status", nodeName) // Use nodeName as item status
                .time(Instant.now(), WritePrecision.MS);

        // Write to InfluxDB
        writeApiBlocking.writePoint(point);
    }
}

