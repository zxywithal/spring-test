package org.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.think.in.spring.ioc.overview.domain.User;

import java.util.Map;

/**
 * yml外部化配置示例
 * map的实现形式
 */
public class XmlBaseYamlPropertySourceDemo {
    public static void main(String[] args) {
        //创建ioc底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //创建xml资源的BeanDefinitionReader
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //加载xml资源文件
        reader.loadBeanDefinitions("META-INF/yml-property-source-context.xml");
        //获取user bean 对象
        Map<String,Object> ymlMap = beanFactory.getBean("ymlMap", Map.class);

        System.out.println(ymlMap);
    }
}
