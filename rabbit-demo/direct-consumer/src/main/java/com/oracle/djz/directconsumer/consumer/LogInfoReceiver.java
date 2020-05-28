package com.oracle.djz.directconsumer.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

//封装类，用来封装消费Info消息队列的类
@Component
@RabbitListener(bindings=@QueueBinding(
        value=@Queue(value="${mq.config.queue.info}",autoDelete = "true"),
        exchange = @Exchange(value="${mq.config.exchange}",type= ExchangeTypes.DIRECT),
        key = "${mq.config.queue.info.routing.key}"
))
public class LogInfoReceiver {
   @RabbitHandler
    public String receiver(String msg){
       System.out.println("Info Receive Servier: "+msg);
    return "Info Server has Done";
   }
}
