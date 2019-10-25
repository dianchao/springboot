package com.dianchao;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//开启基于注解的rabbitmq
@EnableRabbit
@SpringBootApplication
public class SpringBootAmqpApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAmqpApp.class, args);
    }
}
