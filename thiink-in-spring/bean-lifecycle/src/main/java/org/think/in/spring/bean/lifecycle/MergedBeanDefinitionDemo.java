package org.think.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.think.in.spring.ioc.overview.domain.SuperUser;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * BeanDefinition 合并示例
 */
public class MergedBeanDefinitionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //基于xml资源BeanDefinitionReader 实现
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //初始化resource
        ClassPathResource resource = new ClassPathResource("META-INF/dependency-lookup-context.xml");
        //构建EncodeResource 解决编码问题
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        //加载资源文件
        int foundBeanDefinitionCounts = reader.loadBeanDefinitions(encodedResource);
        System.out.println("加载的bean 个数 "+foundBeanDefinitionCounts);
        User bean = beanFactory.getBean("user",User.class);
        System.out.println(bean);

        SuperUser superUser = beanFactory.getBean(SuperUser.class);
        System.out.println(superUser);
    }
}
