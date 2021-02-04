package com.tencent.cloud.tsw.demo.kafka.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducer {
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducer.class, args);
    }
}
