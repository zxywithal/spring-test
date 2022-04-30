package org.think.in.spring.ioc.bean.definition;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * bean 实例化示例
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        //1.静态工程实例化bean
        User user = beanFactory.getBean("user-by-static-method", User.class);
        System.out.println(user);
        //2.实例工厂实例化bean
        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        System.out.println(userByInstanceMethod);
        System.out.println(user == userByInstanceMethod);
        //3.factoryBean 方式实例化bean
        User userFactoryBean = beanFactory.getBean("userFactoryBean", User.class);
        System.out.println(userFactoryBean);
        System.out.println(user == userFactoryBean);
        //4.BeanDefinitionRegistry.registerBeanDefinition 方式实例化bean
        //这种方式目前还有点问题
//        instantiationUserBeanDefinition(beanFactory.getbeanf, "beanDefinitionUser");

    }

    public static void instantiationUserBeanDefinition(BeanDefinitionRegistry registry,String beanName) {
        //获取BeanDefinitionBuilder
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //初始化field
        definitionBuilder.addPropertyValue("id", 1).addPropertyValue("name", "zxy");
        //获取beandefinition
        AbstractBeanDefinition beanDefinition = definitionBuilder.getBeanDefinition();
        //注册beanDefinition
        registry.registerBeanDefinition(beanName,beanDefinition);
    }
}
