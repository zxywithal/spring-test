package org.think.in.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * auto-wiring  constructor 方式自动注入
 */
public class AutoWiringConstructorDependencyConstructorInjectionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory listableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(listableBeanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/autowiring-dependency-constructor-injection.xml");
        System.out.println(listableBeanFactory.getBean(UserHolder.class));

    }
}
