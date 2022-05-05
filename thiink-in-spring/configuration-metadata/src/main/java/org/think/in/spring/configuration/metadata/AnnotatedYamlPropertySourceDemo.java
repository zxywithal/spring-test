package org.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.think.in.spring.ioc.overview.domain.User;
import org.think.in.spring.ioc.overview.enums.City;

import java.util.Map;

/**
 * 基于java 注解的 yml外部化配置示例
 * map的实现形式
 */
@PropertySource(
        name="yamlPropertySource",
        value="META-INF/user.yml",
        factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceDemo {

    /**
     *user.name 是Java Properties默认存在，当前用户zxy，而非配置中定义的张潇赟
     * @param id
     * @param name
     * @return
     */
    @Bean
    public User user(@Value("${user.id}") Long id,
                     @Value("${user.name}") String name,
                     @Value("${user.city}")City city){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCity(city);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedYamlPropertySourceDemo.class);
        context.refresh();
        User user = context.getBean("user", User.class);
        System.out.println(user);
        context.close();
    }
}
