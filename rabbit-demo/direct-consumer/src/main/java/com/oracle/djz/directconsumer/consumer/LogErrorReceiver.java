package com.oracle.djz.directconsumer.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings=@QueueBinding(
        value=@Queue(value="${mq.config.queue.error}",autoDelete = "true"),
        exchange = @Exchange(value="${mq.config.exchange}",type= ExchangeTypes.DIRECT),
        key = "${mq.config.queue.error.routing.key}"
))
public class LogErrorReceiver {
    @RabbitHandler
    public void receiver(String msg){
        System.out.println("Error Receive Servier: "+msg);

    }
}
