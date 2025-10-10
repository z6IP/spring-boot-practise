package top.zhz.springboot.filter_interceptor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.zhz.springboot.filter_interceptor.result.Result;

@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {


    @GetMapping("/pay/{id}")
    public Result<String> pay(@PathVariable long id) {
        log.info("开始支付");
        return Result.ok("支付成功，订单号：" + id);
    }

    @GetMapping("/test/filter")
    public Result<String> testFilter(@RequestParam String name) {
        return Result.ok("Hello, " + name);
    }
}