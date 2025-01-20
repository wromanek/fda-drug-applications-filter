package com.romanek.drugs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.romanek.drugs.fda")
public class DrugsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrugsServiceApplication.class, args);
    }

}
