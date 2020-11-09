package com.alibaba.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xiaoxh
 * @Date: Created in 2020/11/9 15:01
 * @Description：
 * @Version: 0.0.1
 **/
@RestController
public class test {
    @GetMapping("/web")
    public void index(){
        System.out.println("start.............");
    }
}
