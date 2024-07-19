package com.example.natwest_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NatwestProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(NatwestProjectApplication.class, args);
    }

}
