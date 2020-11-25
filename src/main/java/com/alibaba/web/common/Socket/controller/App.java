package com.alibaba.web.common.Socket.controller;

import com.alibaba.web.common.Socket.po.SocketMessage;
import com.alibaba.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: xiaoxh
 * @Date: Created in 2020/11/25 9:34
 * @Description：
 * @Version: 0.0.1
 **/
@Controller
@Slf4j
public class App extends BaseController {

    @Value("${webSocketServerIP}")
    private String webSocketServerIP;
    /**
     * websochet消息发送对象
     */
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * 跳转到测试页面
     * @return
     */
    @RequestMapping("/websocket")
    public String index() {
        request.setAttribute("ip",webSocketServerIP);
        return "websocket";
    }

    //测试页面显示后台消息推送次数
    private long count=0;

    //接收浏览器消息路径设置
    @MessageMapping("/send")
    //服务端向浏览器推送地址设置
    @SendTo("/topic/send")
    public SocketMessage send(SocketMessage message) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.date = "浏览器消息";
        return message;
    }

    //由后台发送到浏览器服务
    @SendTo("/topic/callback")
    //定时5秒给页面推一次数据
    @Scheduled(cron="0/5 * * * * ?")
    public Object callback() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("推送消息了"+df.format(new Date()));
        log.info("websocket推送消息,时间-"+df.format(new Date()));
        //向页面这个地址推送消息
        messagingTemplate.convertAndSend("/topic/callback","客户端消息"+count );
        count++;
        return null;
    }

}
