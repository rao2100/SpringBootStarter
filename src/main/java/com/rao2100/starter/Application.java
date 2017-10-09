package com.rao2100.starter;

import com.rao2100.starter.config.ConfigProperties;
import com.rao2100.starter.config.RsyncProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rao2100"})
@Configuration
@EnableAsync
@EnableScheduling
public class Application {

//    @Autowired
//    ConfigProperties config;
    
    @Autowired
    RsyncProperties configRsync;

    private static final Log logger = LogFactory.getLog(Application.class);

    public static void main(String[] args) {

        System.out.println("Starting Spring Boot Starter");
        SpringApplication app = new SpringApplication(Application.class);
        ApplicationContext ctx = app.run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            
            System.out.println("####### CONFIG ###########");

            System.out.println("config: " + configRsync.getPath());
           
            System.out.println("locations: " + configRsync.getLocationList().size());
            
            for (RsyncProperties.Location loc : configRsync.getLocationList()) {
                System.out.println("loc: " + loc.getSource() + " -> " + loc.getDestination());                
            }
            
//            System.out.println("getting locations");

        };
    }

}
