package org.think.in.spring.ioc.overview.dependency.injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.think.in.spring.ioc.overview.annotation.Super;
import org.think.in.spring.ioc.overview.domain.User;
import org.think.in.spring.ioc.overview.repository.UserRepository;

import java.util.Map;

/**
 * 依赖查找
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        //配置xml文件
        //启动spring上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        //依赖来源一：自定义bean
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        System.out.println(userRepository);
        //依赖来源二：依赖注入（内建依赖)
        System.out.println(userRepository.getBeanFactory() == beanFactory);
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory().getClass().getName());

        System.out.println(userRepository.getObjectFactory().getObject());
        System.out.println(userRepository.getObjectFactory().getObject() == beanFactory);

        //依赖来源三：容器内建bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);

    }

}
