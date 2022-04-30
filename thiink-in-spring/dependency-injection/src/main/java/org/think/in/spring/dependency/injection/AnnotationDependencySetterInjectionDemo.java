package org.think.in.spring.dependency.injection;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * 基于java注解的方式依赖注入
 */
public class AnnotationDependencySetterInjectionDemo {
    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册配置类
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);
        //注解启动的beanFactory，加载xml资源文件
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        //启动应用上下文
        applicationContext.refresh();

        System.out.println(applicationContext.getBean(UserHolder.class));
        //关闭spring 应用上下文
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }

}
