package com.company.service;

import org.drools.core.process.instance.WorkItemHandler;
import org.drools.core.process.instance.impl.WorkItemImpl;
import org.jbpm.process.workitem.rest.RESTWorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.HashMap;

public class PressingHandler implements WorkItemHandler{
    @Bean
    public WorkItemHandler PressingHandler() {
        System.out.println("Instantiating PressingTask Handler");
        return new PressingHandler();
    }
    
    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        System.out.println("Executing PressingTask...");

        Map<String, Object> results = new HashMap<>();
        results.put("Result", "Success");

        manager.completeWorkItem(workItem.getId(), results);
    }

    @Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		manager.abortWorkItem(workItem.getId());
        System.out.println("Aborting Pressing Task...");
	}

    private String PressingTaskRESTCall() {
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
