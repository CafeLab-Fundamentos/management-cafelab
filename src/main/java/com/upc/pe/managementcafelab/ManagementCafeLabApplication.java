package com.upc.pe.managementcafelab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ManagementCafeLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementCafeLabApplication.class, args);
    }

}
