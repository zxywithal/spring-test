package org.think.in.spring.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 依赖注入Environment 示例
 */
@Configuration
public class InjectingEnvironmentDemo  implements EnvironmentAware, ApplicationContextAware {

    private Environment environment;

    @Autowired
    private Environment environment2;
    private ApplicationContext applicationContext;
    @Autowired
    private ApplicationContext applicationContext2;


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(InjectingEnvironmentDemo.class);

        ctx.refresh();
        InjectingEnvironmentDemo bean = ctx.getBean(InjectingEnvironmentDemo.class);
        System.out.println(bean.environment);

        System.out.println(bean.environment == bean.environment2); //true
        System.out.println(bean.environment == ctx.getEnvironment());   //true 两种方式获取的environment 是同一个对象

        System.out.println(bean.environment == bean.applicationContext.getEnvironment());   //true
        System.out.println(bean.environment == bean.applicationContext2.getEnvironment());   //true

        ctx.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
