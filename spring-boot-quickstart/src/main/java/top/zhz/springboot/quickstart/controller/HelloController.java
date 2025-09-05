package top.zhz.springboot.quickstart.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhz
 * @Date: 2025/9/5
 */
@RestController
public class HelloController {

    @Value("${my.mood.feature.helloSwitch}")
    private boolean isHelloEnabled;

    @Value("${my.mood.feature.closeMsg}")
    private String closeMessage;

    @GetMapping("/hello")
    public String hello() {
        if(isHelloEnabled){
            return "接口开放中! 欢迎访问我的第一个 Spring Boot 项目~";
        }else {
            return closeMessage;
        }
    }
}