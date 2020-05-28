package com.oracle.djz.rabbitdemo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
  @RabbitListener(queues = "hello-queue")
    public void Receiver(String msg){
      System.out.println("receiver: "+msg);
  }
}
