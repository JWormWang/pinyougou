package com.oracle.djz.topicconsumer.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings =@QueueBinding(
        value =@Queue(value ="${mq.config.queue.info}",autoDelete ="true"),
        exchange = @Exchange(value = "${mq.config.exchange}",type =ExchangeTypes.TOPIC),
        key ="${mq.config.queue.info.routing.key}"
))
public class LogInfoReceiver {
    @RabbitHandler
    public void receive(String msg){
        System.out.println("Info Receive: "+msg);

    }
}
