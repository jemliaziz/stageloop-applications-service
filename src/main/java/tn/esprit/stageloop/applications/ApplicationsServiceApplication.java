package tn.esprit.stageloop.applications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAspectJAutoProxy
@EnableScheduling
@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationsServiceApplication.class, args);
    }

}
