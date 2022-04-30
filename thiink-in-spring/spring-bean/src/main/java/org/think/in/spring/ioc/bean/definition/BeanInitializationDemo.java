package org.think.in.spring.ioc.bean.definition;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.think.in.spring.ioc.bean.factory.DefaultUserFactory;
import org.think.in.spring.ioc.bean.factory.UserFactory;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * bean 初始化示例
 */
@Configuration
public class BeanInitializationDemo {
    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册配置类
        applicationContext.register(BeanInitializationDemo.class);
        //启动应用上下文
        applicationContext.refresh();

        /**
         * 多个初始化的执行顺序
         * 1.@PostConstruct java标注注解
         * 2.InitializingBean 接口定义的方法
         * 3.@Bean注解或者xml中配置的initMethod
         */
        UserFactory bean = applicationContext.getBean(UserFactory.class);
        System.out.println("spring 准备关闭");
        /**
         * 多个destroy 方法的执行顺序
         * 1.@PreDestroy java标准注解
         * 2.DisposableBean 接口定义的方法
         * 3.@Bean注解或者xml中配置的destroyMethod
         */
        applicationContext.close();
        System.out.println("spring 已关闭");
    }

    @Bean(initMethod = "annotationInitMethod",destroyMethod = "doDestroy")
    @Lazy
    public UserFactory userFactoryBean() {
        return new DefaultUserFactory();
    }
}
