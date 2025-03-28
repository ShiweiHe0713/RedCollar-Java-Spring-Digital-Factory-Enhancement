package com.company.service;

import org.jbpm.kie.services.impl.RuntimeDataServiceImpl;
import org.jbpm.services.api.RuntimeDataService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JBPMConfig {

    @Bean
    public RuntimeDataService runtimeDataService() {
        return new RuntimeDataServiceImpl();
    }
    // @Bean
    // public KieContainer kieContainer() {
    //     return KieServices.Factory.get().getKieClasspathContainer();
    // }

    // @Bean
    // public KieSession kieSession(KieContainer kieContainer) {
    //     KieSession kieSession = kieContainer.newKieSession("ksession-clothing");
    //     return kieSession;
    // }
}