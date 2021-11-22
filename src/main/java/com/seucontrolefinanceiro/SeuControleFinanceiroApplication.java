package com.seucontrolefinanceiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class SeuControleFinanceiroApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeuControleFinanceiroApplication.class, args);
    }
}
