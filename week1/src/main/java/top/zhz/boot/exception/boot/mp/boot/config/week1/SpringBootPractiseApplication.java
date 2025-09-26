package top.zhz.boot.exception.boot.mp.boot.config.week1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootPractiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPractiseApplication.class, args);
    }


    @GetMapping("/hello")
    public String getHello(){
        return "hello world";
    }
}
