package org.think.in.spring.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 依赖注入Environment 示例
 */
@Configuration
public class lookupEnvironmentDemo implements EnvironmentAware {

    private Environment environment;


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(lookupEnvironmentDemo.class);

        ctx.refresh();
        lookupEnvironmentDemo bean = ctx.getBean(lookupEnvironmentDemo.class);
        System.out.println(bean.environment);

        Environment environment1 = ctx.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);

        System.out.println(bean.environment == environment1); //true 依赖查找或者依赖注入都是同一个environment对象


        ctx.close();
    }
}
