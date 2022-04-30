package org.think.in.spring.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * 基于api的方式实现依赖注入
 */
public class ApiDependencySetterInjectionDemo {
    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册配置类
//        applicationContext.register(ApiDependencySetterInjectionDemo.class);
        //注册UserHolder BeanDefinition
        applicationContext.registerBeanDefinition("userHolder", createUserHolderBeanDefinition());
        //注解启动的beanFactory，加载xml资源文件
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        //启动应用上下文
        applicationContext.refresh();

        System.out.println(applicationContext.getBean(UserHolder.class));
        //关闭spring 应用上下文
        applicationContext.close();
    }

    /**
     * 为{@link UserHolder} 创建{@link BeanDefinition}
     * @return
     */
    private static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        //通过属性的方式注入
//        beanDefinitionBuilder.addPropertyReference("user","superUser");
        //构造器没有名称，是因为构造器的参数顺序是固定的。按照执行的代码顺序来绑定参数
        beanDefinitionBuilder.addConstructorArgReference("superUser");
        return beanDefinitionBuilder.getBeanDefinition();
    }

//    @Bean
//    public UserHolder userHolder(User user) {
//        UserHolder userHolder = new UserHolder();
//        userHolder.setUser(user);
//        return userHolder;
//    }

}
