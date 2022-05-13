package org.think.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableHelloWorld
public class EnableMoudleDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AttributeOverridesDemo.class);
        //启动
        ctx.refresh();
        //依赖查找
        String helloWorld = ctx.getBean("helloWorld", String.class);

        System.out.println(helloWorld);
        ctx.close();
    }
}
