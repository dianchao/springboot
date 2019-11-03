package com.dianchao.controller;

import com.dianchao.sevice.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
    @Autowired
    TicketService ticketService;

    @GetMapping("/ticket/{name}")
    public String ticket(@PathVariable("name") String name){
        return ticketService.getTicket(name);
    }
}
