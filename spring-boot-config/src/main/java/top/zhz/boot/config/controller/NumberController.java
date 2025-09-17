package top.zhz.boot.config.controller;

import cn.hutool.core.util.NumberUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhz
 */
@RestController
@RequestMapping("/number")
public class NumberController {

    @GetMapping("/format")
    public String getNumber(@RequestParam long c) {         //百分比计数
        return NumberUtil.decimalFormat(",###", c);
    }
}
