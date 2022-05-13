package org.think.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解属性覆盖示例
 */
@MyComponentScan2(scanBasePackages = "org.think.in.spring.annotation")
//MyComponentScan2 和MyComponentScan 存在同名属性scanBasePackages
public class AttributeOverridesDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AttributeOverridesDemo.class);
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
