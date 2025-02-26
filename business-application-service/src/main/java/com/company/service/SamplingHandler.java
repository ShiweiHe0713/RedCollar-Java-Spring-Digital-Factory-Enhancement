package com.company.service;

import org.drools.core.process.instance.WorkItemHandler;
import org.drools.core.process.instance.impl.WorkItemImpl;
import org.jbpm.process.workitem.rest.RESTWorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.HashMap;

public class SamplingHandler implements WorkItemHandler{
    @Bean
    public WorkItemHandler SamplingHandler() {
        System.out.println("Instantiating SamplingTask Handler");
        return new SamplingHandler();
    }
    
    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        System.out.println("Executing SamplingTask...");

        String fabricType = (String) workItem.getParameter("fabricType");
        String sampleSize = (String) workItem.getParameter("sampleSize");

        System.out.println("Fabric Type: " + fabricType);
        System.out.println("Sample Size: " + sampleSize);

        Map<String, Object> results = new HashMap<>();
        // event should drive the process(like event or users), not custom tasks
        // 
        
        results.put("Result", "Success");

        manager.completeWorkItem(workItem.getId(), results);
    }

    @Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		manager.abortWorkItem(workItem.getId());
        System.out.println("Aborting Sampling Task...");
	}

    private String samplingTaskRESTCall() {
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
