package top.zhz.boot.config.controller;

import cn.hutool.core.comparator.CompareUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhz
 */
@RestController
@RequestMapping("/compare")
public class CompareController {
    @GetMapping("/value")
    public int compare(@RequestParam boolean value) {

        return CompareUtil.compare(null, "a", value);

    }
}
