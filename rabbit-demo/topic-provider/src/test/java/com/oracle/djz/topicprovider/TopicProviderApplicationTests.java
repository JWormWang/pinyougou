package com.oracle.djz.topicprovider;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicProviderApplicationTests {
   @Autowired
    private AmqpTemplate amqpTemplate;

     private String exchangeName="log.topic";

    private String infoRoutingKey="order.log.info";

    private String errorRoutingKey="order.log.error";

    @Test
    public   void contextLoads() {
   amqpTemplate.convertAndSend(exchangeName,infoRoutingKey,"this is info log");
    }

}
