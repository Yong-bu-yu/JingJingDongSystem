package com.example.memallapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@MapperScan("com/example/memallapi/dao")
@SpringBootApplication
public class MemallapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemallapiApplication.class, args);
    }

}
