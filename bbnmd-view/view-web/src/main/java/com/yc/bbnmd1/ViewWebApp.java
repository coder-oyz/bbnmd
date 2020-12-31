package com.yc.bbnmd1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableHystrix
@EnableFeignClients(basePackages = "com.yc.bbnmd1.client")
@EnableCircuitBreaker   //启用断路器

public class ViewWebApp {
    public static void main(String[] args) {
        SpringApplication.run(ViewWebApp.class, args);
    }
}



