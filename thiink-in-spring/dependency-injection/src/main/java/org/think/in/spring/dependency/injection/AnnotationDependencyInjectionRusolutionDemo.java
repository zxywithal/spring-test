package org.think.in.spring.dependency.injection;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.think.in.spring.ioc.overview.domain.User;

import java.util.List;

/**
 * 注解驱动的依赖注入过程
 */
public class AnnotationDependencyInjectionRusolutionDemo {

    //实时注入 + 通过类型 + 字段名称 查找
    @Autowired
    private User user;
    @Autowired
    private ObjectProvider<User> objectProvider;
    @Autowired
    private ObjectFactory<List<User>> objectFactory;

    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册配置类
        applicationContext.register(AnnotationDependencyInjectionRusolutionDemo.class);
        //注解启动的beanFactory，加载xml资源文件
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        //启动应用上下文
        applicationContext.refresh();

        AnnotationDependencyInjectionRusolutionDemo bean = applicationContext.getBean(AnnotationDependencyInjectionRusolutionDemo.class);

        System.err.println("bean.user :" + bean.user);
        System.err.println("bean.objectProvider.getObject : " + bean.objectProvider.getObject());
        System.err.println("===================");
        bean.objectProvider.forEach(System.err::println);
        System.err.println("===================");
        System.err.println("bean.objectFactory.getObject : "+bean.objectFactory.getObject());
        //关闭spring 应用上下文
        applicationContext.close();
    }
}
