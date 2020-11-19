package com.alibaba.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: xiaoxh
 * @Date: Created in 2020/11/9 13:01
 * @Description：
 * @Version: 0.0.1
 **/
@SpringBootApplication
@EnableScheduling
@EnableSwagger2
@EnableAsync(proxyTargetClass = true)
@MapperScan(value = {"com.alibaba.**.dao"})
public class NavigateMap extends SpringBootServletInitializer {

    public static ApplicationContext cs;


    public static void main(String[] args) {
        SpringApplication.run(NavigateMap.class, args);
    }
}
