package com.dianchao;

import com.dianchao.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootAmqpAppTests {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    //单播消息
    @Test
    public void contextLoad(){
        //Message需要自己构造一个，定义消息体内容和消息头
        //rabbitTemplate.send(exchange, routeKey, message);

        //Object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
        //rabbitTemplate.convertAndSend(exchange, routeKey, object);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("helloworld", 23, true));
        //对象被默认序列化以后发送出去
        //rabbitTemplate.convertAndSend("exchange.direct", "aliyun.news", map);
        rabbitTemplate.convertAndSend("exchange.direct", "aliyun.news", new Book("西游记", "吴承恩"));
    }

    //接收消息
    @Test
    public void receive(){
        Object object = rabbitTemplate.receiveAndConvert("aliyun.news");
        System.out.println("============> " + object.getClass().getName());
        System.out.println(object);
    }

    //广播消息
    @Test
    public void broadcastMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("三国演义", "罗贯中"));
    }

    @Test
    public void createExchange(){
        //创建exchange
        //amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));

        //创建queue
        //amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));

        //绑定
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE, "amqpadmin.exchange", "amqp.haha", null));
    }
}
