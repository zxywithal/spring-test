package org.think.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.think.in.spring.ioc.overview.domain.SuperUser;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * bean 实例化 演示
 * bean 属性赋值 演示
 * aware接口回调 演示
 */
public class BeanInstantiationLifeCycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
        System.out.println("========================");
        executeApplicationContext();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //方法1：添加BeanPostProcessor实例,且DefaultListableBeanFactory 只能是手动添加的方式
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //基于xml资源BeanDefinitionReader 实现
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        int foundBeanDefinitionCounts = reader.loadBeanDefinitions(locations);
        System.out.println("加载的bean 个数 " + foundBeanDefinitionCounts);
        User bean = beanFactory.getBean("user", User.class);
        System.out.println(bean);

        SuperUser superUser = beanFactory.getBean(SuperUser.class);
        System.out.println(superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }

    public static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        //方法2：将MyInstantiationAwareBeanPostProcessor作为bean注册到xml配置中，且applicationContext只能作为bean在xml注入
        //基于xml资源BeanDefinitionReader 实现
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        applicationContext.setConfigLocations(locations);
        //启动上下文
        applicationContext.refresh();
        User bean = applicationContext.getBean("user", User.class);
        System.out.println(bean);

        SuperUser superUser = applicationContext.getBean(SuperUser.class);
        System.out.println(superUser);

        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
        //关闭上下文
        applicationContext.close();
    }

}
