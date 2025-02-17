package com.company.service;

import org.drools.core.process.instance.WorkItemHandler;
import org.drools.core.process.instance.impl.WorkItemImpl;
import org.jbpm.process.workitem.rest.RESTWorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.HashMap;

public class PackagingHandler implements WorkItemHandler{
    @Bean
    public WorkItemHandler PackagingHandler() {
        System.out.println("Instantiating PackagingTask Handler");
        return new PackagingHandler();
    }
    
    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        System.out.println("Executing PackagingTask...");

        Map<String, Object> results = new HashMap<>();
        results.put("Result", "Success");

        manager.completeWorkItem(workItem.getId(), results);
    }

    @Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		manager.abortWorkItem(workItem.getId());
        System.out.println("Aborting Packaging Task...");
	}

    private String PackagingTaskRESTCall() {
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
