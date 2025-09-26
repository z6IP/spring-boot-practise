package top.zhz.springboot.filter_interceptor.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("MyInterceptor pre Handler:{}", requestURI);
        LocalDateTime beginTime = LocalDateTime.now();
        request.setAttribute("beginTime", beginTime);
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LocalDateTime beginTime = (LocalDateTime) request.getAttribute("beginTime");
        log.info("begin Time:{}", beginTime);
        LocalDateTime endTime = LocalDateTime.now();
        String requestURI = request.getRequestURI();
        log.info("MyInterceptor拦截器执行结束: {},{}", requestURI, endTime);
        log.info("请求耗时:{}", (endTime.getNano() - beginTime.getNano()) / 1000000 + "ms");
    }
}
