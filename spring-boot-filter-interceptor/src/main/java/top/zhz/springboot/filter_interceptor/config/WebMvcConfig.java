package top.zhz.springboot.filter_interceptor.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.zhz.springboot.filter_interceptor.interceptor.BusinessLogInterceptor;
import top.zhz.springboot.filter_interceptor.interceptor.ParamValidateInterceptor;
import top.zhz.springboot.filter_interceptor.interceptor.RoleAuthInterceptor;
import top.zhz.springboot.filter_interceptor.interceptor.TimeStatInterceptor;

@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final TimeStatInterceptor timeStatInterceptor;
    private final BusinessLogInterceptor businessLogInterceptor;
    private final RoleAuthInterceptor roleAuthInterceptor;
    private final ParamValidateInterceptor paramValidateInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 注册参数校验拦截器
        registry.addInterceptor(paramValidateInterceptor)
                .addPathPatterns("/api/*")
                // 优先于权限拦截器
                .order(0);

        // 注册耗时统计拦截器
        registry.addInterceptor(timeStatInterceptor)
                // 拦截所有路径
                .addPathPatterns("/**")
                // 排除静态资源和错误页
                .excludePathPatterns("/static/**", "/error")
                // 执行顺序（值越小越先执行）
                .order(1);

        // 注册业务日志拦截器
        registry.addInterceptor(businessLogInterceptor)
                .addPathPatterns("/**")
                // 晚于 TimeStatInterceptor 执行
                .order(2);

        // 注册权限拦截器
        registry.addInterceptor(roleAuthInterceptor)
                .addPathPatterns("/api/**")
                // 注册登录接口不拦截
                .excludePathPatterns("/user/login", "/user/register")
                // 晚于 TimeStatInterceptor 执行
                .order(3);
    }


}