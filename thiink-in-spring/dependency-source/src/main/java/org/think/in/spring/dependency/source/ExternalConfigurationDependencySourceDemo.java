package org.think.in.spring.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource(value = {"META-INF/default.properties"},encoding = "ISO-8859-1")
public class ExternalConfigurationDependencySourceDemo {

    @Value("${user.id:-1}")
    private Long id;
    @Value("${usr.name}")
    private String name;

    @Value("${user.resource:classpath://default.properties")
    private Resource resource;

    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class （配置类） 也是spring bean
        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);

        //启动应用上下文
        applicationContext.refresh();
        ExternalConfigurationDependencySourceDemo bean = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);
        System.out.println("bean.id :" + bean.id);
        System.out.println("bean.resource :" + bean.resource);
        System.out.println("bean.name :" + bean.name);

        //关闭spring 应用上下文
        applicationContext.close();
    }
}
