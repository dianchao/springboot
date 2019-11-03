package com.dianchao.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dianchao.sevice.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
    public String getTicket(String name) {
        return name + "买票成功！";
    }
}
