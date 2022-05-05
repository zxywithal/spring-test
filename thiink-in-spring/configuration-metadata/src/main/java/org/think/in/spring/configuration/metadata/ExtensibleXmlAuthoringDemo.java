package org.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * spring xml 元素扩展示例
 */
public class ExtensibleXmlAuthoringDemo {
    public static void main(String[] args) {
        //创建ioc底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //创建xml资源的BeanDefinitionReader
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //加载xml资源文件
        reader.loadBeanDefinitions("META-INF/users-context.xml");
        //获取user bean 对象
        User bean = beanFactory.getBean(User.class);

        System.out.println(bean);

    }
}
