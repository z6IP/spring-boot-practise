package top.zhz.boot.exception.boot.mp.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhz.boot.exception.boot.mp.entity.UserAccount;
import top.zhz.boot.exception.boot.mp.mapper.UserAccountMapper;
import top.zhz.boot.exception.boot.mp.service.UserAccountService;

/**
 * @author zhz
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {
}
