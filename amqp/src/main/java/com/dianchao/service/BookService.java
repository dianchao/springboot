package com.dianchao.service;

import com.dianchao.bean.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    //监听消息队列的内容
    @RabbitListener(queues = "aliyun.news")
    public void receiveMessage(Book book){
        System.out.println("收到消息: " + book);
    }
}
