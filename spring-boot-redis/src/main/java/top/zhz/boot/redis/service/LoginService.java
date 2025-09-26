package top.zhz.boot.redis.service;

import top.zhz.boot.redis.entity.LoginRequest;
import top.zhz.boot.redis.entity.LoginResponse;


public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
}