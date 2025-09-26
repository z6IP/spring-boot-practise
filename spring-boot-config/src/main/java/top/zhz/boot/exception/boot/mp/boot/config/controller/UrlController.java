package top.zhz.boot.exception.boot.mp.boot.config.controller;

import cn.hutool.core.util.URLUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhz
 */
@RestController
@RequestMapping("/url")
public class UrlController {
    @GetMapping("/test")
    public String test(@RequestParam String url) {

        return URLUtil.normalize(url);

    }
}
