package org.think.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * bean 元信息配置
 * 给予properties文件配置javabean
 */
public class BeanMetadataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //读取properties文件，加载bean
        PropertiesBeanDefinitionReader definitionReader = new PropertiesBeanDefinitionReader(factory);
        //初始化resource
        ClassPathResource resource = new ClassPathResource("META-INF/user.properties");
        //构建EncodeResource 解决编码问题
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        //加载资源文件
        int foundBeanDefinitionCounts = definitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("加载的bean 个数 "+foundBeanDefinitionCounts);
        User bean = factory.getBean(User.class);
        System.out.println(bean);
    }
}
