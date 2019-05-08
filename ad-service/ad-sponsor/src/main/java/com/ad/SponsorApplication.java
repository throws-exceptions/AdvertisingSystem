package com.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * created by Mr.F on 2019/5/1
 */
@EnableFeignClients //可以用来调用其他微服务，但是这里使用是以便监控
@EnableCircuitBreaker //同样是方便监控
@EnableEurekaClient //表明是eurekaclient
@SpringBootApplication
public class SponsorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SponsorApplication.class,args);
    }
}
