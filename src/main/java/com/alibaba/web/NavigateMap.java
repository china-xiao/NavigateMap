package com.alibaba.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: xiaoxh
 * @Date: Created in 2020/11/9 13:01
 * @Description：
 * @Version: 0.0.1
 **/
@SpringBootApplication
@EnableScheduling
public class NavigateMap {
    public static void main(String[] args) {
        SpringApplication.run(NavigateMap.class, args);
    }
}
