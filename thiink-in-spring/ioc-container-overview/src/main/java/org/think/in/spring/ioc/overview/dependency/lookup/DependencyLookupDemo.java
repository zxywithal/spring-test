package org.think.in.spring.ioc.overview.dependency.lookup;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.think.in.spring.ioc.overview.annotation.Super;
import org.think.in.spring.ioc.overview.domain.User;

import java.util.Map;

/**
 * 依赖查找
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        //按类型查找
        lookupByType(beanFactory);
        //按类型查找集合
        lookupCollectionByType(beanFactory);
        //按名称查找
        lookupInRealTime(beanFactory);
        //按名称延时查找
        lookupInLazyTime(beanFactory);
        //按注解查找 集合类型的查找
        lookupByAnnotation(beanFactory);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            //集合类型查找
            Map<String, User> beansOfType = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("按注解查找对象："+beansOfType);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            //集合查找
            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查到到的所有的user集合对象："+beansOfType);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean( User.class);
        System.out.println("按类型实时查找"+user);
    }

    private static void lookupInLazyTime(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>)beanFactory.getBean("objectFactory");
        System.out.println("延时查找"+objectFactory.getObject());
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user", User.class);
        System.out.println("按id实时查找"+user);
    }
}
