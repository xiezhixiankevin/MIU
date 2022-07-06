package com.example.miu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.miu.mapper"})
public class MiuApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiuApplication.class, args);
    }

}
