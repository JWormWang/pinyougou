package com.oracle.djz.rabbitdemo.provider.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    @Bean
    public Queue crateQueue(){
      //创建一个队列，注册在Spring容器中，且RabbitMQ软件中就创建了这个队列
        return new Queue("hello-queue");
    }
}
