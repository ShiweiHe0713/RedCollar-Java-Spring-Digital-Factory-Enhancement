package com.company.red_collar.controller;

import org.jbpm.services.api.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kie.api.runtime.process.ProcessInstance;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import com.company.red_collar.RedCollarApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.write.Point;
import com.influxdb.client.domain.WritePrecision;
import org.kie.api.runtime.KieSession;

@RestController
@RequestMapping("/task")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(RedCollarApplication.class);
    // private final String PROCESS_ID = "ClothingProcess";
    @Autowired
    private ProcessService processService;

    private final String deploymentId = "Evaluation_1.0.0-SNAPSHOT";

    private final String processId = "ClothingProcess";
    // @Autowired
    // private KieSession kieSession;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
    // InfluxDB properties
    @Value("${influxdb.url}")
    private String influxDbUrl;

    @Value("${influxdb.token}")
    private String influxDbToken;

    @Value("${influxdb.bucket}")
    private String bucket;

    @Value("${influxdb.org}")
    private String org;

    private InfluxDBClient influxDBClient;

     * Initialize the InfluxDB client after the bean is constructed.
     @PostConstruct
     public void initializeInfluxDB() {
        influxDBClient = InfluxDBClientFactory.create(influxDbUrl, influxDbToken.toCharArray());
    }
    */

    /**
     * RabbitMQ Listener for Incoming Orders.
     * Automatically starts the BPMN process when an order message is received.
     */
    @RabbitListener(queues = "order_queue")
    public void newOrder(String orderMessage) {
        try {
            // Parse the order message
            Long processInstanceId = -1L;
            // Map<String, Object> orderData = objectMapper.readValue(orderMessage, Map.class);
            Map<String, Object> processVariables = new HashMap<>();
            // processVariables.put("orderId", orderId);
            processVariables.put("inspectionResult", true); // Default inspection result
            processVariables.put("status", "Started");
            processInstanceId = processService.startProcess(deploymentId, processId, processVariables);
            logger.info("Created process " + processInstanceId);
            // String orderId = (String) orderData.get("order_id");

            // Start the BPMN process for the order
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Private Helper Method to Start a BPMN Process.
     */
    // private void startProcess(String orderId) {
        // Map<String, Object> processVariables = new HashMap<>();
        // processVariables.put("orderId", orderId);
        // processVariables.put("inspectionResult", true); // Default inspection result
        // processVariables.put("status", "Started");

        // ProcessInstance processInstance = kieSession.startProcess(PROCESS_ID, processVariables);
        
        // if (processInstance != null) {
        //     logToInfluxDB("Process Started", orderId, "Started");
        //     System.out.println("Process started successfully for Order ID: " + orderId);
        // } else {
        //     System.out.println("Failed to start process for Order ID: " + orderId);
        // }
    // }

    /**
     * Endpoints for Completing Each Task in the Process.
     */

    // @PostMapping("/sampling")
    // public String completeSampling(@RequestBody Map<String, Object> inputData) {
    //     return completeTask("Sampling", inputData);
    // }

    // @PostMapping("/laying")
    // public String completeLaying(@RequestBody Map<String, Object> inputData) {
    //     return completeTask("Laying", inputData);
    // }

    // @PostMapping("/marking")
    // public String completeMarking(@RequestBody Map<String, Object> inputData) {
    //     return completeTask("Marking", inputData);
    // }

    // @PostMapping("/cutting")
    // public String completeCutting(@RequestBody Map<String, Object> inputData) {
    //     return completeTask("Cutting", inputData);
    // }

    // @PostMapping("/stitching")
    // public String completeStitching(@RequestBody Map<String, Object> inputData) {
    //     return completeTask("Stitching", inputData);
    // }

    // @PostMapping("/checking")
    // public String completeChecking(@RequestBody Map<String, Object> inputData) {
    //     return completeTask("Checking", inputData);
    // }

    // @PostMapping("/pressing")
    // public String completePressing(@RequestBody Map<String, Object> inputData) {
    //     return completeTask("Pressing", inputData);
    // }

    // @PostMapping("/packaging")
    // public String completePackaging(@RequestBody Map<String, Object> inputData) {
    //     return completeTask("Packaging", inputData);
    // }

    // @PostMapping("/manualQualityCheck")
    // public String completeManualQualityCheck(@RequestBody Map<String, Object> inputData) {
    //     return completeTask("Manual Quality Check", inputData);
    // }

    /**
     * Private Helper Method to Complete a Task.
     * This method interacts with jBPM to signal task completion.
     *
     * @param taskName  The name of the task being completed.
     * @param inputData Input data required for the task (e.g., `orderId`).
     */
    // private String completeTask(String taskName, Map<String, Object> inputData) {
    //     try {
    //         String orderId = (String) inputData.get("orderId");
    //         Boolean inspectionResult = (Boolean) inputData.getOrDefault("inspectionResult", true);
    //         inputData.put("status", taskName);
    //         System.out.println("Completing task: " + taskName + " for Order ID: " + orderId);
    //         System.out.println(orderId + " " + inspectionResult + " " + inputData.get("status"));

    //         // Log task completion to InfluxDB
    //         logToInfluxDB(taskName, orderId, "Completed");

    //         // Update the global variable for manual tasks
    //         if (taskName.equalsIgnoreCase("Manual Quality Check")) {
    //             kieSession.setGlobal("inspectionResult", inspectionResult);
    //         }

    //         return "Task " + taskName + " completed for Order ID: " + orderId;
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return "Error completing task " + taskName + ": " + e.getMessage();
    //     }
    // }

    /**
     * Helper Method to Log Task Details to InfluxDB.
     *
     * @param taskName The name of the task being logged.
     * @param orderId  The associated order ID.
     * @param status   The status of the task (e.g., "Completed").
     */
    // private void logToInfluxDB(String taskName, String orderId, String status) {
    //     try {
    //         Point point = Point.measurement("task_timeline")
    //                 .addTag("taskName", taskName)
    //                 .addTag("orderId", orderId)
    //                 .addField("status", status)
    //                 .time(Instant.now(), WritePrecision.MS);

    //         influxDBClient.getWriteApiBlocking().writePoint(bucket, org, point);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         System.out.println("Error logging to InfluxDB: " + e.getMessage());
    //     }
    // }
}
