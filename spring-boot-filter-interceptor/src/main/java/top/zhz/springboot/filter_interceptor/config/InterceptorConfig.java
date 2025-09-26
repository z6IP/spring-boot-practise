package top.zhz.springboot.filter_interceptor.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.zhz.springboot.filter_interceptor.interceptor.MyInterceptor;
import top.zhz.springboot.filter_interceptor.interceptor.YourInterceptor;

@Configuration
@Slf4j
@AllArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final MyInterceptor myInterceptor;

    private final YourInterceptor yourInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/test");
        registry.addInterceptor(new YourInterceptor()).addPathPatterns("/test");
    }


}
