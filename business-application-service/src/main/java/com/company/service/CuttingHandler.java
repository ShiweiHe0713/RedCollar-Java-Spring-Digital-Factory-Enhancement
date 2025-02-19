package com.company.service;

import org.drools.core.process.instance.WorkItemHandler;
import org.drools.core.process.instance.impl.WorkItemImpl;
import org.jbpm.process.workitem.rest.RESTWorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.HashMap;

public class CuttingHandler implements WorkItemHandler{
    @Bean
    public WorkItemHandler CuttingHandler() {
        System.out.println("Instantiating CuttingTask Handler");
        return new CuttingHandler();
    }
    
    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        System.out.println("Executing CuttingTask...");

        String cuttingMethod = (String) workItem.getParameter("cuttingMethod");
        String pieceCount = (String) workItem.getParameter("pieceCount");

        System.out.println("cutting method: " + cuttingMethod);
        System.out.println("Piece count: " + pieceCount);

        Map<String, Object> results = new HashMap<>();
        results.put("Result", "Success");

        manager.completeWorkItem(workItem.getId(), results);
    }

    @Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		manager.abortWorkItem(workItem.getId());
        System.out.println("Aborting Cutting Task...");
	}

    private String CuttingTaskRESTCall() {
        String result = "";

        try {
            RESTWorkItemHandler handler = new RESTWorkItemHandler();
			WorkItemImpl workItem = new WorkItemImpl();
            result += "success";
        }

        catch(Exception e) {
            result += "Error";
        }
        return result;
    }
}
