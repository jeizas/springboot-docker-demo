package com.jeizas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by jeizas on 2017/1/1.
 */
@Controller
public class GreetingController {

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(value = "helloSocket")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "helloSocket1")
    public String indexs() {
        return "index1";
    }
//    /**
//     * 接受客户端公告的socket请求
//     * @param value
//     */
//    @MessageMapping("/change-notice")
//    public void greeting(String value){
//        //把指定的消息进行序列化，通过MessageConverter方法进行包装成一条消息，发送到指定的客户端
//        this.simpMessagingTemplate.convertAndSend("/topic/notice", value);
//    }

    @MessageMapping("/change-notice")
    @SendTo("/topic/notice")
    public String greeting(String value) {
        System.out.println(value);
        return value;
    }

    @MessageMapping("/message")
    @SendToUser("/message")
    public void userMessage(String msg) throws Exception {
        simpMessagingTemplate.convertAndSendToUser(msg.substring(0, msg.indexOf("@") - 1), "/message", msg);
    }
}
