package org.think.in.spring.dependency.lookup;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * 类型安全 依赖查找示例
 */
public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        //启动应用上下文
        applicationContext.refresh();

        //演示BeanFactory#getBean 方法安全性
        displayBeanFactoryGetBean(applicationContext);
        //演示ObjectFactory#getBean 方法安全性
        displayObjectFactoryGetBean(applicationContext);
        //演示ObjectProvider#getBean 方法安全性
        displayObjectProivderGetBean(applicationContext);

        //演示集合类型的方法安全性
        displayListableBeanFactory(applicationContext);

        applicationContext.close();
    }

    public static void displayListableBeanFactory(ListableBeanFactory beanFactory) {
        printBeansException("displayListableBeanFactory", () -> beanFactory.getBeansOfType(User.class));
    }

    /**
     * 如果没有bean 或者bean 不唯一
     *
     * @param beanFactory
     */
    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    public static void displayObjectFactoryGetBean(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);

        printBeansException("displayObjectFactoryGetBean", () -> userObjectProvider.getObject());
    }

    public static void displayObjectProivderGetBean(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);

        printBeansException("displayObjectProivderGetBean", () -> userObjectProvider.getIfAvailable());
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("================================= ");
        System.err.println(source + " 开始执行");
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
