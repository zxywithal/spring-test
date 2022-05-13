package org.think.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


/**
 * {@link ComponentScan}
 * @see Component
 * @see ComponentScan
 */
//basePackages 指定在当前包以及子包 扫描
//@ComponentScan(basePackages = "org.think.in.spring.annotation")
@MyComponentScan(scanBasePackages = "org.think.in.spring.annotation")
public class ComponentScanDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ComponentScanDemo.class);
        //启动
        ctx.refresh();
        //依赖查找
//        TestClass 标注了@MyComponent2，
//        @MyComponent2 又被标注了@MyComponent，
//        @MyComponent 又被Component标注
        //从spring 4.0开始支持了多层次的派生
        TestClass test2 = ctx.getBean("testClass", TestClass.class);
        System.out.println(test2);
        ctx.close();
    }
}
