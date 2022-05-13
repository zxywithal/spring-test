package org.think.in.spring.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent2
@MyConfiguration(name = "my-application")
public @interface MyApplication {
}
