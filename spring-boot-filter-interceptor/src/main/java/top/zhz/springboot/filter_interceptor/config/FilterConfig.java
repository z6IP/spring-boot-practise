package top.zhz.springboot.filter_interceptor.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.zhz.springboot.filter_interceptor.filter.MyFilter;
import top.zhz.springboot.filter_interceptor.filter.YourFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyFilter> myFilterFilterRegistrationBean() {
        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/test");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<YourFilter> yourFilterFilterRegistrationBean() {
        FilterRegistrationBean<YourFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new YourFilter());
        registrationBean.addUrlPatterns("/test");
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
