package top.zhz.boot.exception.boot.mp.boot.config.controller;

import org.springframework.web.bind.annotation.*;
import top.zhz.boot.exception.boot.mp.boot.config.common.ApiResponse;
import top.zhz.boot.exception.boot.mp.boot.config.enums.DrinkType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhz
 */
@RestController
@RequestMapping("/drink")
public class DrinkController {
    @GetMapping("/{type}")
    public ApiResponse<Map<String, Object>> chooseDrink(@PathVariable String type) {
        try {
            DrinkType drink = DrinkType.valueOf(type.toUpperCase());
            Map<String, Object> result = new HashMap<>();
            result.put("name", drink.getLabel());
            result.put("money", drink.getMoney());
            return ApiResponse.success("选择成功", result);
        } catch (IllegalArgumentException e) {
            return ApiResponse.error("饮料选择无效");
        }
    }

    @GetMapping("/menu")
    public ApiResponse<List<Map<String, Object>>> getMenu() {
        List<Map<String, Object>> menu = new ArrayList<>();
        for (DrinkType drink : DrinkType.values()) {
            Map<String, Object> item = new HashMap<>();
            item.put("name", drink.getLabel());
            item.put("money", drink.getMoney());
            menu.add(item);
        }
        return ApiResponse.success("菜单获取成功", menu);
    }

    @GetMapping("/order")
    public ApiResponse<Map<String, Object>> orderDrink(@RequestParam String type,@RequestParam int num) {
        try {
            DrinkType drink = DrinkType.valueOf(type.toUpperCase());
            double total = drink.getMoney() * num;
            Map<String, Object> result = new HashMap<>();

            result.put("name", drink.getLabel());
            result.put("num", num);
            result.put("unitPrice", drink.getMoney());
            result.put("totalMoney", total);
            return ApiResponse.success("下单成功", result);
        }catch (IllegalArgumentException e){
            return ApiResponse.error("饮料类型无效");
        }
    }



}
