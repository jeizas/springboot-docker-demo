package com.jeizas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jeizas on 2016/12/16.
 */
@RestController//相当于@Controller 和 @RequestBody
//@Controller
public class HelloController {

    /**
     * URL传参数
     * @param id
     * @return
     */
//    @RequestMapping(value = "hello/{id}", method = RequestMethod.GET)
    @GetMapping(value = "hello/{id}")
    public String say(@PathVariable("id") Integer id, @RequestParam(value = "v", required = false, defaultValue = "0") Integer version) {
        return "hello" + "-id:" + id + ",version:" + version;
    }

    @RequestMapping(value = "index", method =  RequestMethod.GET)
    public String hello() {
        return "index";
    }
}
