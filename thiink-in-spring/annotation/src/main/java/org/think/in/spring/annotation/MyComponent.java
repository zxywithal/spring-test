package org.think.in.spring.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

//验证Component的派生性
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyComponent {
}
