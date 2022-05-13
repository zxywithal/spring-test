package org.think.in.spring.annotation;


import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyConfiguration {
    /**
     * 名称属性
     * @return
     */
    String name();
}
