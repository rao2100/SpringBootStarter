package com.rao2100.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rao2100"})
public class Application {

    private static final Log logger = LogFactory.getLog(Application.class);
    
    public static void main(String[] args) {
        
        
        System.setProperty("com.rao2100.*", "INFO");

        System.out.println("Starting Spring Boot Starter");
        SpringApplication app = new SpringApplication(Application.class);
    }

}
