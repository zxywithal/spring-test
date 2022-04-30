package org.think.in.spring.ioc.bean.definition;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * 依赖查找
 */
public class BeanAliasDemo {
    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");

        User user = beanFactory.getBean("user", User.class);
        User zxyUser = beanFactory.getBean("zxyUser", User.class);
        System.out.println(user == zxyUser);
    }


}
