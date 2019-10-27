package com.dianchao.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    //second(秒), minute（分钟）, hour（时）, day of month（日）, month（月），day of week（周几）
    //@Scheduled(cron = "0 * * * * SUN-SAT")
    //@Scheduled(cron = "0,1,2,3,4 * * * * SUN-SAT")
    //@Scheduled(cron = "0-4 * * * * SUN-SAT")
    @Scheduled(cron = "0/4 * * * * SUN-SAT") //从0秒开始每4秒执行一次
    public void hello(){
        System.out.println("hello......");
    }
}
