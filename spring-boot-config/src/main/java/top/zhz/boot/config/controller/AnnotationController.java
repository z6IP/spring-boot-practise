package top.zhz.boot.config.controller;

import cn.hutool.core.annotation.AnnotationUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhz.boot.config.service.AnnotationForTest;

import java.lang.reflect.Method;

/**
 * @author zhz
 */
@RestController
public class AnnotationController {

    @GetMapping("/annotation")
    @AnnotationForTest("测试")
    public String test() {
        try {
            // 获取当前方法上的注解值
            Method method = AnnotationController.class.getMethod("test");
            Object value = AnnotationUtil.getAnnotationValue(method, AnnotationForTest.class);
            return "这是一个注解" + value;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
