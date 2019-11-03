package com.dianchao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/buy/{name}")
    public String buyTicket(@PathVariable("name") String name){
        return restTemplate.getForObject("http://EUREKA-PROVIDER/ticket/" + name, String.class);
    }
}
