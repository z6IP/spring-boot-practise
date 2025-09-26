package top.zhz.boot.exception.boot.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.zhz.boot.exception.boot.mp.entity.UserAccount;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserAccountMapperTest {

    @Resource
    private UserAccountMapper userAccountMapper;

    @Test
    void testUserAccount() {
        UserAccount userAccount = userAccountMapper.selectById(1);
        assertEquals("zhangwei", userAccount.getUsername());
    }

    @Test
    void testUserAccount2() {
        Wrapper<UserAccount> wrapper = new QueryWrapper<>();
        Long count = userAccountMapper.selectCount(wrapper);
        assertEquals(50, count);
    }

    @Test
    void testInsert() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("zzh");
        userAccount.setNickname("zzh");
        userAccount.setEmail("1967430835@qq.com");
        userAccount.setPhone("12345678901");
        userAccount.setStatus(1);
        userAccount.setDeleted(0);
        userAccount.setVersion(0);
        int row = userAccountMapper.insert(userAccount);
        assertEquals(1, row);
    }

    @Test
    void updateTest() {
        UserAccount userAccount = userAccountMapper.selectById(52);
        userAccount.setNickname("zzh11111");
        int row = userAccountMapper.updateById(userAccount);
        assertEquals(1, row);
    }

}