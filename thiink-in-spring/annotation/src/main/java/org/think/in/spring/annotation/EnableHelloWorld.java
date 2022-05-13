package org.think.in.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//方法1 通过Configuration class实现   通过@Import 注解导入具体实现
//@Import(HelloWorldConfiguration.class)
//方法2 通过ImportSelector 接口实现
//@Import(HelloWorldImportSelector.class)
//方法3 通过ImportBeanDefinitionRegister 实现
@Import(HelloWorldImportBeanDefinitionRegister.class)
public @interface EnableHelloWorld {
}
