package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import java.util.*;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.model.ProcessInstanceDesc;
import org.jbpm.services.api.model.UserTaskInstanceDesc;
import org.kie.internal.query.QueryFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.ProcessResponse;

import com.icegreen.greenmail.spring.GreenMailBean;
@RestController
@ImportAutoConfiguration(GreenMailBean.class)
@RequestMapping("/process")
public class ProcessController {
//    private static final Logger logger = LoggerFactory.getLogger(ServerApplication.class);
    // @Autowired
    private RuntimeDataService runtimeDataService;

    private final String processId = "ClothingProcess";

    @GetMapping("/all")
    public ResponseEntity<List<ProcessResponse>> getAllProcesses() {
        List<ProcessResponse> all = new ArrayList<ProcessResponse>();

        Collection<ProcessInstanceDesc> instances = runtimeDataService
        .getProcessInstancesByProcessDefinition(processId, new QueryFilter());
        
        instances.forEach(instance -> {
        Long instanceId = instance.getId();
        List<Long> taskIds = runtimeDataService.getTasksByProcessInstanceId(instanceId);
        if (taskIds == null) {
            return;
        }

        System.out.println("   TASKS:");
        taskIds.forEach(taskId -> {
            UserTaskInstanceDesc task = runtimeDataService.getTaskById(taskId);
            System.out.println(taskId);
            all.add(new ProcessResponse(instanceId, task.getName(), task.getStatus()));
        });
        });

        return ResponseEntity.ok(all);
    }

    // @GetMapping("/status/{orderId}")
    // public String getOrderStatus(@PathVariable String orderId) {
    //     try {
    //         Collection<ProcessInstanceDesc> instances = runtimeDataService
    //         .getProcessInstancesByProcessId(processId);
            
    //         Long instanceId = instance.getId();
    //         Long TaskId = 

    //         if (!taskId.isEmpty()) {;
    //             return "Order ID" + orderId + " is currently in task: " + task.getName();
    //         }
    //         return "No active tasks for Order ID: " + orderId;
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return "Error fetching order status: " + e.getMessage();
    //     }
    // }
}
