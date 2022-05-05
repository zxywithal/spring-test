package org.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * 基于xml资源的 yml外部化配置示例
 * map的实现形式
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {
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

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(resource.getResource());
        Properties yamlProperties = yamlPropertiesFactoryBean.getObject();
        return new PropertiesPropertySource(name, yamlProperties);
    }
}
