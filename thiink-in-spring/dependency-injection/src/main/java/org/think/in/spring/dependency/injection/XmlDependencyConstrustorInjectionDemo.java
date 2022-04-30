package org.think.in.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于xml的方式构造器注入
 */
public class XmlDependencyConstrustorInjectionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory listableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(listableBeanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-constructor-injection.xml");
        System.out.println(listableBeanFactory.getBean(UserHolder.class));

    }
}
