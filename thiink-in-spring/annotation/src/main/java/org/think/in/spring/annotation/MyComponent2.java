package org.think.in.spring.annotation;

import java.lang.annotation.*;

/**
 * MyComponent 的派生
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyComponent2 {
}
