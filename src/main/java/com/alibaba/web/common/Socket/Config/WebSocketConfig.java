package com.alibaba.web.common.Socket.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @Author: xiaoxh
 * @Date: Created in 2020/11/25 9:31
 * @Description：
 * @Version: 0.0.1
 **/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //设置浏览器接收的服务前缀,同时也是后台服务推送的前缀
        config.enableSimpleBroker("/topic");
        //设置浏览器发送消息的服务前缀,也就是后台服务接收前台信息的前缀
        config.setApplicationDestinationPrefixes("/app");
    }

    //添加服务端点 浏览器链接这个地址
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/my-websocket").withSockJS();
    }

}
