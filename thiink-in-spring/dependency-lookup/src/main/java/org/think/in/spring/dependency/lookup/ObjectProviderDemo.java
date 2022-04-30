package org.think.in.spring.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.think.in.spring.ioc.overview.domain.User;


/**
 * {@link ObjectProvider} 示例
 * 延迟查找示例
 *
 */
//@Configuration 是非必要注解
public class ObjectProviderDemo {
    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        lookupByObjectProvider(applicationContext);

        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);
        applicationContext.close();
    }

    @Bean
    public String message() {
        return "Message";
    }

    @Bean
    @Primary
    public String helloWorld() {
        return "helloWorld";
    }
    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        System.out.println(beanProvider.getIfAvailable(User::createUser));
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        //迭代方式1
//        Iterator<String> iterator = beanProvider.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
        //迭代方式2
//        beanProvider.stream().forEach(System.out::println);
        //迭代方式3
        Iterable<String> stringIterable = beanProvider;
        stringIterable.forEach(System.out::println);
    }
}
