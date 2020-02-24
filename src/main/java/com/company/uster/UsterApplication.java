package com.company.uster;

import com.company.uster.application.util.SampleDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class UsterApplication {

    @Autowired
    private SampleDataGenerator sampleDataGenerator;

    @PostConstruct
    public void init() {
        sampleDataGenerator.generate();
    }

    public static void main(String[] args) {
        SpringApplication.run(UsterApplication.class, args);
    }
}
