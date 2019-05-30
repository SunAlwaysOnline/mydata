package com.sun.mydata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
public class MydataApplication {

    public static void main(String[] args) {
        SpringApplication.run(MydataApplication.class, args);
    }

}
