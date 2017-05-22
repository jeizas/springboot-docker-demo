package com.jeizas.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by jeizas on 2017/1/1.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

    /**
     * 添加一个服务端点，来接受客户端的链接
     * @param stompEndpointRegistry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //客户端可以根据/socket与服务端开启连接，并且开启可sockJS的支持
        stompEndpointRegistry.addEndpoint("/socket").withSockJS();
    }

    /**
     * 定义详细代理，也就是设置消息连接请求的各种规范信息
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        super.configureMessageBroker(registry);
        //客户端接受服务端消息的前缀信息
        registry.enableSimpleBroker("/topic", "/user");
        //客户端发给服务端消息的前缀信息
        registry.setApplicationDestinationPrefixes("/app");
        //点对点的发送消息
        registry.setUserDestinationPrefix("/user");
    }
}
