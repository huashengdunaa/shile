package com.heima.pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Sl
 * @Description: TODO
 * @DateTime: 2025/12/22
 * @Version 1.0
 **/
@EnableFeignClients(basePackages = "com.hmall.api.client")
@MapperScan("com.heima.pay.mapper")
@SpringBootApplication
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}