package top.zhz.boot.redis.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.zhz.boot.redis.cache.RedisCache;
import top.zhz.boot.redis.cache.RedisKeys;
import top.zhz.boot.redis.entity.LoginRequest;
import top.zhz.boot.redis.entity.LoginResponse;
import top.zhz.boot.redis.enums.ErrorCode;
import top.zhz.boot.redis.exception.ServerException;
import top.zhz.boot.redis.service.LoginService;
import top.zhz.boot.redis.utils.CommonUtils;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final RedisCache redisCache;

    /**
     * 用户登录验证
     * @param loginRequest 登录请求参数（包含手机号和验证码）
     * @return 登录响应结果（包含token和用户信息）
     */
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String phone = loginRequest.getPhone();
        String inputCode = loginRequest.getCode();

        // 1. 校验手机号格式
        if (!CommonUtils.checkPhone(phone)) {
            throw new ServerException(ErrorCode.PHONE_ERROR);
        }

        // 2. 校验验证码是否为空
        if (inputCode == null || inputCode.trim().isEmpty()) {
            throw new ServerException("验证码不能为空");
        }

        // 3. 从Redis中获取验证码
        String redisKey = RedisKeys.getSmsKey(phone);
        Object redisCodeObj = redisCache.get(redisKey);

        // 处理Redis中无数据的情况
        String redisCode = redisCodeObj != null ? redisCodeObj.toString() : null;

        // 4. 验证码不存在或已过期
        if (redisCode == null) {
            throw new ServerException("验证码已过期或不存在");
        }

        // 5. 验证码不匹配
        if (!inputCode.equals(redisCode)) {
            throw new ServerException("验证码错误");
        }

        // 6. 验证成功，删除Redis中的验证码（防止重复使用）
        redisCache.delete(redisKey);

        // 7. 生成token并返回登录信息
        String token = generateToken(phone);
        log.info("用户 {} 登录成功", phone);

        return new LoginResponse(token, phone);
    }

    /**
     * 生成登录令牌
     * 注意：实际生产环境建议使用JWT等更安全的方式生成token
     * @param phone 手机号
     * @return 生成的token字符串
     */
    private String generateToken(String phone) {
        // 简单实现，实际项目中建议使用JWT并设置过期时间
        return UUID.randomUUID().toString().replace("-", "")
                + "." + System.currentTimeMillis()
                + "." + phone.hashCode();
    }
}