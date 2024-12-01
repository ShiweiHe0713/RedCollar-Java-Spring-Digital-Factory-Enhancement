package com.company.red_collar;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;
import org.springframework.stereotype.Service;

@Service
public class ClothingProcessService {

    private final KieSession kieSession;

    public ClothingProcessService(KieSession kieSession) {
        this.kieSession = kieSession;
    }

    public void startProcess(String orderMessage) {
        System.out.println("Starting process for order: " + orderMessage);
        ProcessInstance processInstance = kieSession.startProcess("ClothingProcess");
        if (processInstance != null) {
            System.out.println("Order ID: " + orderMessage + " Process started with ID: " + processInstance.getId());
        } else {
            System.out.println("Failed to start process for order ID: " + orderMessage);
        }
    }
}
