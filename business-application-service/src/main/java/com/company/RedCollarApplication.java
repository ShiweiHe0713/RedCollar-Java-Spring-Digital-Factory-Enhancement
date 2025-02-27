package com.company;

import org.codehaus.plexus.component.annotations.Component;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.company.repositories.*") // ✅ Ensure repositories are scanned
@EntityScan(basePackages = "com.company.model.*") // ✅ Forces Spring Boot to scan for JPA entities
public class RedCollarApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedCollarApplication.class, args);
    }
}