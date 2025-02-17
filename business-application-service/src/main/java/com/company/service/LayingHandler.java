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
