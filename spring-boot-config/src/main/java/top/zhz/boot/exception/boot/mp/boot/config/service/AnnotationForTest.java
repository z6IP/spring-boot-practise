package top.zhz.boot.exception.boot.mp.boot.config.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhz
 */
@Retention(RetentionPolicy.RUNTIME)

@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface AnnotationForTest {
    /*
     * 注解的默认属性值
     *
     * @return 属性值
     */

    String value();
}
