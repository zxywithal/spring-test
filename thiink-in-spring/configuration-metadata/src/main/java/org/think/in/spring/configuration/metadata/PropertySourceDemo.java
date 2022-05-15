package org.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.think.in.spring.ioc.overview.domain.User;

import java.util.*;

/**
 * PropertySource 示例
 * 新增比系统属性优先级更高的属性配置，
 */
@PropertySource("classpath:META-INF/user-bean-definition.properties")
public class PropertySourceDemo {

    /**
     * user.name 是Java Properties默认存在，当前用户zxy，而非配置中定义的张潇赟
     * @param id
     * @param name
     * @return
     */
    @Bean
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //扩展Environment中的PropertySource
        //添加PropertySource 的操作必须在refresh方法之前完成
        Map<String, Object> mySource = new HashMap<>();
        mySource.put("user.name", "红红火火恍恍惚惚");
        MapPropertySource mapPropertySource = new MapPropertySource("first-property-source",mySource);
        //在系统属性之前加入自定义资源配置，
        context.getEnvironment().getPropertySources().addFirst(mapPropertySource);

        context.register(PropertySourceDemo.class);
        //启动spring应用上下文
        context.refresh();
        //beanName 和bean映射
        Map<String, User> usersMap = context.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : usersMap.entrySet()) {
            System.out.printf("User Bean name : %s , content :%s \n", entry.getKey(), entry.getValue());
        }
        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        System.out.println(propertySources);
        for (org.springframework.core.env.PropertySource<?> propertySource : propertySources) {
            Object source = propertySource.getSource();
            System.out.println(source);
            System.out.println("===========");
        }
        //关闭spring应用上下文
        context.close();
    }
}
