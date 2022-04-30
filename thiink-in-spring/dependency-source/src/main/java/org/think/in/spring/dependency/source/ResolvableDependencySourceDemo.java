package org.think.in.spring.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

public class ResolvableDependencySourceDemo {

    @Autowired
    private String str;

    @PostConstruct
    public void init() {
        System.out.println(str);
    }

    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        ConfigurableListableBeanFactory configurableListableBeanFactory = applicationContext.getBeanFactory();
//        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        // 注册Configuration Class （配置类） 也是spring bean
        applicationContext.register(ResolvableDependencySourceDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory ->{
            beanFactory.registerResolvableDependency(String.class, "Hello,World");
        });
        //启动应用上下文
        applicationContext.refresh();
        //关闭spring 应用上下文
        applicationContext.close();
    }
}
