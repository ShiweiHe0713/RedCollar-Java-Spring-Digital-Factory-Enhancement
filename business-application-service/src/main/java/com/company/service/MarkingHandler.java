package com.company.service;

import org.drools.core.process.instance.WorkItemHandler;
import org.drools.core.process.instance.impl.WorkItemImpl;
import org.jbpm.process.workitem.rest.RESTWorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.HashMap;

public class MarkingHandler implements WorkItemHandler{
    @Bean
    public WorkItemHandler MarkingHandler() {
        System.out.println("Instantiating MarkingTask Handler");
        return new MarkingHandler();
    }
    
    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        System.out.println("Executing MarkingTask...");

        Map<String, Object> results = new HashMap<>();
        results.put("Result", "Success");

        manager.completeWorkItem(workItem.getId(), results);
    }

    @Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		manager.abortWorkItem(workItem.getId());
        System.out.println("Aborting Marking Task...");
	}

    private String MarkingTaskRESTCall() {
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
