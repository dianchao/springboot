package com.dianchao.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dianchao.sevice.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Reference
    TicketService ticketService;

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name){
        return ticketService.getTicket(name);
    }
}
