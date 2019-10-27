package com.dianchao.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
    //表示这是一个异步执行方法
    @Async
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("处理数据中...");
    }
}
