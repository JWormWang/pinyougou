package com.oracle.djz.topicconsumer.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings =@QueueBinding(
        value =@Queue(value ="${mq.config.queue.logall}",autoDelete ="true"),
        exchange = @Exchange(value = "${mq.config.exchange}",type =ExchangeTypes.TOPIC),
        key ="${mq.config.queue.logall.routing.key}"
))
public class LogAllReceiver {
    @RabbitHandler
    public void receive(String msg){
        System.out.println("LogAll Receive: "+msg);

    }
}
