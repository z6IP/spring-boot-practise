package top.zhz.boot.config.springboot.quickstart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhz
 * @date 2025/9/4
 * @description Application
 **/
@SpringBootApplication
@RestController

public class QuickstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickstartApplication.class, args);
    }

    @RequestMapping("/hello")
    public String helloWorld() {
        return "hello world.";
    }
}
