package top.zhz.springbootwebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 启用定时任务
public class SpringBootWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebsocketApplication.class, args);
    }

}
