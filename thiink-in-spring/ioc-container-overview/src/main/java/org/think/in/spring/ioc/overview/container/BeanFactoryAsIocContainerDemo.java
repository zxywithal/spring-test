package org.think.in.spring.ioc.overview.container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.think.in.spring.ioc.overview.domain.User;

import java.util.Map;

/**
 * ioc 容器示例
 */
public class BeanFactoryAsIocContainerDemo {
    public static void main(String[] args) {
        //创建beanFactory 容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        int beanDefinitions = reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        System.out.println("Bean 定义数量 :"+beanDefinitions);
        lookupCollectionByType(factory);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查到到的所有的user集合对象："+beansOfType);
        }
    }
}
