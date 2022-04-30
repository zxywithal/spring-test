package org.think.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.think.in.spring.ioc.overview.domain.SuperUser;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * bean 实例初始化 演示
 */
public class BeanInitializationLifeCycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //方法1：添加BeanPostProcessor实例 BeanFactory 只能用手动添加
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //添加annotation post processor 如果没有这个，且没有applicationContext上下文，则基于@PostConstruct注解的初始化方法不会回调
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        //方法2：将MyInstantiationAwareBeanPostProcessor作为bean注册到xml配置中
        //基于xml资源BeanDefinitionReader 实现
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        int foundBeanDefinitionCounts = reader.loadBeanDefinitions(locations);
        System.out.println("加载的bean 个数 " + foundBeanDefinitionCounts);

        //显示的执行preInstantiateSingletons 方法
        //如果在ApplicationContext 上下文 SmartInitializingSingleton 会自动自动执行
        beanFactory.preInstantiateSingletons();

        User bean = beanFactory.getBean("user", User.class);
        System.out.println(bean);

        SuperUser superUser = beanFactory.getBean(SuperUser.class);
        System.out.println(superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);

        System.out.println(userHolder);
    }

    public static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
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
