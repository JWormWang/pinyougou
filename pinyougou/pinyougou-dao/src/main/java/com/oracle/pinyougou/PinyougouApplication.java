package com.oracle.pinyougou;

import com.oracle.pinyougou.mapper.TbUserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class PinyougouApplication {

    public static void main(String[] args) {
           SpringApplication.run(PinyougouApplication.class, args);
    }

}
