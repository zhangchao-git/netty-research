package com.taishan.iot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.taishan.iot.dao")
public class NettyResearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyResearchApplication.class, args);
    }

}
