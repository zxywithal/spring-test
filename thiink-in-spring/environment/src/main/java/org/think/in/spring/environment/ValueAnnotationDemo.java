package org.think.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValueAnnotationDemo {

    @Value("${user.name}")
    private String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(ValueAnnotationDemo.class);

        ctx.refresh();
        ValueAnnotationDemo bean = ctx.getBean(ValueAnnotationDemo.class);
        System.out.println(bean.userName);
        ctx.close();
    }
}
