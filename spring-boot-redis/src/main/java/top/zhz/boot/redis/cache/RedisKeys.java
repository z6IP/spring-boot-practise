package top.zhz.boot.redis.cache;

/**
 * @Author: zhz
 * @Date: 2025/9/24
 */
public class RedisKeys {

    /**
     * 验证码 Key
     */
    public static String getSmsKey(String phone) {
        return "sms:captcha:" + phone;
    }
}