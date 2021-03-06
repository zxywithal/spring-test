package org.think.in.spring.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 自定义{@link ComponentScan}
 * 隐性别名示例
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@MyComponentScan
public @interface MyComponentScan2 {

    //隐形别名，通过集成关系表达隐射关系
    //相当于子类里面的scanBasePackages 属性和 ComponentScan 中的basePackages 等价
    //如果不这么做，语法层面就会要求 两个属性都需要赋值
    //类似多态，子注解提供了新的属性方法引用"父"注解中的属性方法
    @AliasFor(annotation = MyComponentScan.class, attribute = "scanBasePackages")
    String[] basePackages() default {};
    //scanBasePackages ->
    //          @AliasFor @ComponentScan.basePackages ->
    //                  @AliasFor @ComponentScan.value

    /**
     * MyComponentScan同名属性
     * @return
     *
     */
    String[] scanBasePackages() default {};

    //packages 覆盖了当前注解 和元（父）注解的scanBasePackages字段
    @AliasFor("scanBasePackages")
    String[] packages() default {};
}
