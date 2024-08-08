package com.newprocess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class AndroidApiProcessApplication {

    public static void main(String[] args) {
        SpringApplication.run(AndroidApiProcessApplication.class, args);
    }
}
