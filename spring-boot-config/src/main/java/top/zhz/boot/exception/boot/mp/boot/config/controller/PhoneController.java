package top.zhz.boot.exception.boot.mp.boot.config.controller;

import cn.hutool.core.util.PhoneUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhz
 */
@RestController
@RequestMapping("/phone")
public class PhoneController {
    @GetMapping("/check")
    private boolean is(@RequestParam String phone){

        return PhoneUtil.isPhone(phone);

    }

}
