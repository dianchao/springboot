package com.dianchao.service;

import com.dianchao.sevice.TicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket(String name) {
        return name + "购票成功!";
    }
}
