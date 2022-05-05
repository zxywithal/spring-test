package org.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.think.in.spring.ioc.overview.domain.User;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 基于java 注解spring Ioc 容器元信息配置示例
 * 将当前类作为Configuration Class
 */
@ImportResource("classpath:META-INF/dependency-lookup-context.xml")
@Import(User.class)
//java8 以后PropertySource 可以支持配置多个（Repeatable）
@PropertySource("classpath:META-INF/user-bean-definition.properties")
//@PropertySources({@PropertySource(""),@PropertySource("")})
public class AnnotatedSpringIocContainerMetadataConfigurationDemo {

    /**
     *
     * @param id
     * @param name
     * @return
     */
    @Bean
    public User configuredUser(@Value("${user.id}") Long id,@Value("${user.name}") String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedSpringIocContainerMetadataConfigurationDemo.class);
        //启动spring应用上下文
        context.refresh();
        //beanName 和bean映射
        Map<String, User> usersMap = context.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : usersMap.entrySet()) {
            System.out.printf("User Bean name : %s , content :%s \n", entry.getKey(), entry.getValue());
        }

        //关闭spring应用上下文
        context.close();
    }
}
