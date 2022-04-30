package org.think.in.spring.ioc.bean.definition;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * bean 注册示例
 * 1.通过@bean 注册
 * 2.通过@Component 方式注册
 * 3.通过@Import 导入
 * 4.java api 命名的方式注册bean
 * 5.java api 非命名方式注册bean
 */
//3.通过@Import 导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册配置类
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        //4.java api 命名的方式注册bean
        registerUserBeanDefinition(applicationContext, "java-api-user");
        //5.java api 非命名方式注册bean
        registerUserBeanDefinition(applicationContext,null);
        //启动应用上下文
        applicationContext.refresh();
        //1.通过@bean 注册
        System.out.println("config 类型所有bean :" + applicationContext.getBeansOfType(Config.class));
        System.out.println("user 类型所有bean :" + applicationContext.getBeansOfType(User.class));


        //关闭spring 应用上下文
        applicationContext.close();
    }

    /**
     * 通过java api方式注册bean
     * @param registry
     * @param beanName
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        AbstractBeanDefinition beanDefinition = getBeanDefinition(User.class);
        if (StringUtils.hasText(beanName)) {
            //如果有bean name
            registry.registerBeanDefinition(beanName, beanDefinition);
        } else {
            //如果没有bean name 则调用registerWithGeneratedName 自动生成bean name
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
        }
    }

    public static AbstractBeanDefinition getBeanDefinition(Class<?> clazz) {
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        definitionBuilder.addPropertyValue("id", 1).addPropertyValue("name", "zxy");
        //获取beandefinition
        AbstractBeanDefinition beanDefinition = definitionBuilder.getBeanDefinition();
        return beanDefinition;
    }

    //2.通过@Component 方式注册
    @Component
    public static class Config {
        @Bean(name = {"user", "zxyUser"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("zxy");
            return user;
        }
    }
}
