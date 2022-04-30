package org.think.in.spring.ioc.bean.definition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.think.in.spring.ioc.bean.factory.DefaultUserFactory;
import org.think.in.spring.ioc.bean.factory.UserFactory;

/**
 * bean 初始化示例
 */
@Configuration
public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("userFactory", new DefaultUserFactory());
        //启动应用上下文
        applicationContext.refresh();

        UserFactory bean = applicationContext.getBean(UserFactory.class);
        System.out.println(bean);
        applicationContext.close();

    }

}
