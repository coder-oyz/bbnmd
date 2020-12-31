package com.yc.bbnmd1;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableZuulProxy
@EnableHystrix
@EnableFeignClients(basePackages = "com.yc.bbnmd1.client")
@EnableCircuitBreaker   //启用断路器
@SpringCloudApplication
public class PostWebApp {
    public static void main(String[] args) {
        SpringApplication.run(PostWebApp.class, args);
    }
}



