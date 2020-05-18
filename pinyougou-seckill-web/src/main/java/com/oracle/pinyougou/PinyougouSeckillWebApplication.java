package com.oracle.pinyougou;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class PinyougouSeckillWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PinyougouSeckillWebApplication.class, args);
	}

}
