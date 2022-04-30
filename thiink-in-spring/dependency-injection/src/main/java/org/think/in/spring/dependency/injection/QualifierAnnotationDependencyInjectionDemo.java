package org.think.in.spring.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.think.in.spring.dependency.injection.annotation.UserGroup;
import org.think.in.spring.ioc.overview.domain.User;

import java.util.Collection;

public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User namedUser;

    //上下文中有4个user
    /**
     * user
     * superUser
     * user1
     * user2
     */

    //2 个 user=user+superUser
    @Autowired
    private Collection<User> userCollection;

    //2个标记为Qualifier 的user  + 2 个标记为UserGroup 的user
    @Autowired
    @Qualifier
    private Collection<User> qualifierUserCollection;

    //2个标记为UserGroup 的user
    @Autowired
    @UserGroup
    private Collection<User> groupedUsers;

    @Bean
    @Qualifier
    public User user1() {
        User user = createuser(7L);
        return user;
    }

    @Bean
    @Qualifier
    public User user2() {
        User user = createuser(8L);
        return user;
    }
    @Bean
    @UserGroup
    public User user3() {
        User user = createuser(9L);
        return user;
    }

    @Bean
    @UserGroup
    public User user4() {
        return createuser(10L);
    }

    private User createuser(long l) {
        User user = new User();
        user.setId(l);
        return user;
    }


    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册配置类
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);
        //注解启动的beanFactory，加载xml资源文件
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        //启动应用上下文
        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo bean = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        //输出superUser
        System.out.println(bean.user);
        //输出user
        System.out.println(bean.namedUser);
        //输出4个user
        System.out.println("bean.userCollection : "+bean.userCollection);
        //输出2个qualifier user  + 2个UserGroup的user
        System.out.println("bean.qualifierUserCollection :"+bean.qualifierUserCollection);
        System.out.println("bean.groupedUsers : "+bean.groupedUsers);
        //关闭spring 应用上下文
        applicationContext.close();
    }
}
