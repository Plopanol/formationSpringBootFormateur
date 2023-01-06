package com.training.store;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class StoreApiApplication {

    private static final Logger logger = LoggerFactory.getLogger(StoreApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StoreApiApplication.class, args);
    }
}
