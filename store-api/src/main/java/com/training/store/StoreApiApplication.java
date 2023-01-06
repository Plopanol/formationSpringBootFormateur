package com.training.store;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class StoreApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreApiApplication.class, args);
    }
}
