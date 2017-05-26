package com.jeizas.controller;

import com.google.gson.Gson;
import com.jeizas.dao.StudentMapper;
import com.jeizas.entity.Student;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by jeizas on 2017/1/1.
 */
@Controller
public class GreetingController {

    private final static Logger logger = Logger.getLogger(GreetingController.class);

    public static Set<Student> loginUsers = new HashSet<>();

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping(value = "helloSocket")
    public String index() {
        return "index";
    }

    @GetMapping(value = "login")
    @ResponseBody
    public Map<String, Object> login(@RequestParam("id") Integer id) {
        Map<String, Object> retMap = new HashMap<>();
        if (null == id) {
            return null;
        }
        Student stu = studentMapper.findById(id);
        if (stu == null) {
            return null;
        }

        loginUsers.add(stu);
        for (Student s : loginUsers) {
            if (s.getId() == id) {
                continue;
            }
            simpMessagingTemplate.convertAndSendToUser(String.valueOf(s.getId()), "/message", "有新用户登录");
        }

        retMap.put("list", loginUsers);
        simpMessagingTemplate.convertAndSend("/topic/notice", retMap);
        retMap.put("obj", stu);
        return retMap;
    }

    /**
     * 接受客户端公告的socket请求
     * @param value
     */
    @MessageMapping("/change-notice")
    public void greeting(String value){
        //把指定的消息进行序列化，通过MessageConverter方法进行包装成一条消息，发送到指定的客户端
        simpMessagingTemplate.convertAndSend("/topic/notice", value);
    }

//    @MessageMapping("/change-notice")
//    @SendTo("/topic/notice")
//    public String greeting(String value) {
//        logger.info(value);
//        return value;
//    }

    @MessageMapping("/message")
    @SendToUser("/message")
    public void userMessage(String msg) throws Exception {
        logger.info(msg);
        simpMessagingTemplate.convertAndSendToUser(msg.substring(0, msg.indexOf("@") - 1), "/message", msg);
    }
}
