package top.zhz.boot.redis.entity;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class StudentTest {

    @Resource
    private RedisTemplate<String,Student> redisTemplate;

    @Test
    void test() {
        Address address = Address.builder()
                .province("广东省")
                .city("深圳市")
                .build();

        Student student = Student.builder()
                .id(1)
                .name("zhz")
                .address(address)
                .build();
        redisTemplate.opsForValue().set("stu1",student,10, TimeUnit.SECONDS);
    }

}