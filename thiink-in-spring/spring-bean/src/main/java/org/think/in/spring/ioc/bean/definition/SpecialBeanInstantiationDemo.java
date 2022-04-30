package org.think.in.spring.ioc.bean.definition;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.think.in.spring.ioc.bean.factory.UserFactory;
import org.think.in.spring.ioc.overview.domain.User;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * bean 实例化示例
 */
public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {
        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        serviceLoadDemo();
    }

    public static void serviceLoadDemo() {
        ServiceLoader<UserFactory> loader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        Iterator<UserFactory> iterator = loader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }
}
