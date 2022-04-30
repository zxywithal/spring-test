package org.think.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.think.in.spring.ioc.overview.domain.User;

public class AnnotatedBeanDefinitionParsingDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //读取properties文件，加载bean
        AnnotatedBeanDefinitionReader definitionReader = new AnnotatedBeanDefinitionReader(factory);
        int beanDefinitionCountBefore = factory.getBeanDefinitionCount();
        definitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCountAfter = factory.getBeanDefinitionCount();
        int beanDefinitionCount = beanDefinitionCountAfter - beanDefinitionCountBefore;
        System.out.println("已加载BeanDefinition 数量: " + beanDefinitionCount);
        //普通的class作为Component注册到spring ioc 容器后 通常名称为 类名称首字母小写
        //Bean 名称生成来自于BeanNameGenerator ，注解实现AnotatedBeanNameGenerator
        AnnotatedBeanDefinitionParsingDemo bean = factory.getBean(AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(bean);
    }
}
