package com.demo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UsersAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersAppApplication.class, args);
    }

}
