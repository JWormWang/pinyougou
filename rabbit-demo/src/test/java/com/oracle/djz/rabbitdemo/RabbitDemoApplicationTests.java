package com.oracle.djz.rabbitdemo;


import com.oracle.djz.rabbitdemo.provider.SendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitDemoApplicationTests {
   @Autowired
   private SendService sendService;
    @Test
  public   void contextLoads() {
        sendService.send("hello 1 world!");
        sendService.send("hello 2 world!");
        sendService.send("hello 3 world!");
        sendService.send("hello 4 world!");
        sendService.send("hello 6 world!");
    }

}
