package com.company.service;

import org.drools.core.process.instance.impl.WorkItemImpl;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import java.util.Map;

public class PackagingTaskRESTWorkItemManager implements WorkItemManager{
    private WorkItem workItem;

	public PackagingTaskRESTWorkItemManager(WorkItemImpl workItem2) {
		workItem = workItem2;
	}
	
	@Override
	public void completeWorkItem(long id, Map<String, Object> results) {
		((WorkItemImpl)workItem).setResults(results);
	}

	@Override
	public void abortWorkItem(long id) {}

	@Override
	public void registerWorkItemHandler(String workItemName, WorkItemHandler handler) {}
}
