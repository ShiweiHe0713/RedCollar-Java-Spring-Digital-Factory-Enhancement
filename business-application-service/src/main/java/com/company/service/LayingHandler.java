package com.company.service;

import org.drools.core.process.instance.WorkItemHandler;
import org.drools.core.process.instance.impl.WorkItemImpl;
import org.jbpm.process.workitem.rest.RESTWorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.HashMap;

public class LayingHandler implements WorkItemHandler{
    @Bean
    public WorkItemHandler LayingHandler() {
        System.out.println("Instantiating LayingTask Handler");
        return new SamplingHandler();
    }

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        // will be trigger by the message "sampling completed"(event)
        // "sampling completed" can be sent from our curl or script to randomly generate messages as HTTP POST
        
        // scenarios: We have two orders(processes), they are in different status(some in sampling, some in laying). 
        // Right now we are using HTTP POST to send messages to the processes, simulating factory has completed those tasks
        // Every message will have order_id as an identifier to update the status of each order
        // queue to keep track of orders, how many completed, 
        // The data in DB is transienet(except things like users etc.), can't query db for progress update. must need MQ for this purpose
        // depends on how event triggered in the process,
        // JBPM evetn documetn: https://docs.jbpm.org/7.74.1.Final/jbpm-docs/html_single/#bpmn-events-con_business-processes
            // how the events get triggered
        // have a consumer in spring boot application that consumes message, then it triggers these event 

        System.out.println("Executing LayingTask...");

        String fabricType = (String) workItem.getParameter("fabricType");
        String layingMethod = (String) workItem.getParameter("layingMethod");

        System.out.println("Fabric Type: " + fabricType);
        System.out.println("laying Method: " + layingMethod);

        Map<String, Object> results = new HashMap<>();
        results.put("Result", "Success");

        manager.completeWorkItem(workItem.getId(), results);
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		manager.abortWorkItem(workItem.getId());
        System.out.println("Aborting Laying Task...");
	}

}
