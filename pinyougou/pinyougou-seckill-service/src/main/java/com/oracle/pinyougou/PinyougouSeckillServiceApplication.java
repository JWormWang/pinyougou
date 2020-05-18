package com.oracle.pinyougou;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableDubbo
public class PinyougouSeckillServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PinyougouSeckillServiceApplication.class, args);
	}

}
