package org.think.in.spring.configuration.metadata;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * properties配置bean示例
 */
public class PropertiesBeanDefinitionReaderDemo {
    public static void main(String[] args) {
        //创建ioc底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //创建properties 资源的Beandefinition
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        //perperties 资源加载默认通过iso-8859-1，实际存储使用的是utf-8
        //下面解决properties文件中文乱码问题
        //创建resource方式1
//        ClassPathResource classPathResource = new ClassPathResource("META-INF/user-bean-definition.properties");
//        EncodedResource encodedResource = new EncodedResource(classPathResource,"utf-8");
//        int beanDefinitionCount = beanDefinitionReader.loadBeanDefinitions(encodedResource);
//        System.out.println(String.format("已加载%d个 BeanDefinition",beanDefinitionCount));

        //创建resource方式2
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:META-INF/user-bean-definition.properties");
        EncodedResource encodedResource2 = new EncodedResource(resource,"utf-8");
        int beanDefinitionCoun2 = beanDefinitionReader.loadBeanDefinitions(encodedResource2);
        System.out.println(String.format("已加载%d个 BeanDefinition",beanDefinitionCoun2));


        //通过依赖查找的方式获取UserBean
        User bean = beanFactory.getBean("user",User.class);
        System.out.println(bean);
    }
}
