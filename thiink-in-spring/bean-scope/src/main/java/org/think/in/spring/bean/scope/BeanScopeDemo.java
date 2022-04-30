package org.think.in.spring.bean.scope;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.think.in.spring.ioc.overview.domain.User;

import java.util.Map;

/**
 * 依赖来源示例
 * 1.prototype 对象无论是依赖查找还是依赖注入都是新生成的对象
 * 2.集合中prototype 和singleton对象都存在，却prototype对象会重新生成
 * 3.singleton 和prototype Bean均会执行初始化回调，仅singleton 会执行销毁回调
 * 4.ConfigurableListableBeanFactory 接口中可以根据beanName获取 beanDefinition，
 *      beanDefinition中isPrototype 熟悉可以判断当前bean是否为prototype
 */
public class BeanScopeDemo implements DisposableBean {

    @Bean
    public User singletonUser() {
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User prototypeUser() {
        return createUser();
    }
    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;
    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser3;
    @Autowired
    private Map<String,User> collectionUser;
    @Autowired
    private ConfigurableListableBeanFactory beanFactory;
    private static User createUser() {

        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class （配置类） 也是spring bean
        applicationContext.register(BeanScopeDemo.class);
        //启动应用上下文
        applicationContext.refresh();

        for (int i = 0; i < 3; i++) {
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println("singletonUser "+singletonUser);
            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("prototypeUserUser "+prototypeUser);
        }

        BeanScopeDemo bean = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("bean.singletonUser : "+bean.singletonUser);
        System.out.println("bean.singletonUser1 : "+bean.singletonUser1);
        //prototype 对象无论是依赖查找还是依赖注入都是新生成的对象
        System.out.println("bean.singletonUser : "+bean.prototypeUser);
        System.out.println("bean.prototypeUser1 : "+bean.prototypeUser1);
        System.out.println("bean.prototypeUser2 : "+bean.prototypeUser2);
        System.out.println("bean.prototypeUser3 : "+bean.prototypeUser3);
        //集合中prototype 和singleton对象都存在，却prototype对象会重新生成

        System.out.println("bean.collectionUser : "+bean.collectionUser);
        //关闭spring 应用上下文
        applicationContext.close();
    }


    @Override
    public void destroy() throws Exception {
        prototypeUser.destory();
        prototypeUser1.destory();
        prototypeUser2.destory();
        prototypeUser3.destory();
        for (Map.Entry<String, User> entry : collectionUser.entrySet()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(entry.getKey());
            if (beanDefinition.isPrototype()) {
                entry.getValue().destory();
            }
        }
    }
}
