package com.oracle.djz.directprovider.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LogInfoSend {
    @Value("${mq.config.exchange}")
    private String exchangeName;
    @Value("${mq.config.queue.info.routing.key}")
    private String routingKey;
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String msg){
    //amqpTemplate.convertAndSend(exchangeName,routingKey,msg);
   String result= (String)amqpTemplate.convertSendAndReceive(exchangeName,routingKey,msg);
      System.out.println(result);
    }
}
