package com.oracle.djz.rabbitdemo.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendService {
    @Autowired
    //消息发送的模板类
    private AmqpTemplate amqpTemplate;
    //发送消息的方法
    public void send(String msg){
//向消息队列（“hello-queue”）发送消息 向哪个队列发送什么消息("消息已二进制方式存储发送")
        amqpTemplate.convertAndSend("hello-queue", msg);
    }

}
