package com.atguigu.yygh.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author:厚积薄发
 * @create:2022-08-05-14:35
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据自动配置
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.atguigu"})
public class ServiceOssApplication {
    public static void main(String[] args){
        SpringApplication.run(ServiceOssApplication.class,args);
    }
}
