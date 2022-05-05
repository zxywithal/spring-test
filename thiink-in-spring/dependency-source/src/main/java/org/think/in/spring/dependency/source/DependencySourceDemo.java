package org.think.in.spring.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源示例
 */
public class DependencySourceDemo implements InitializingBean, DisposableBean {

    //注入在postProcessProperties方法执行，早于setter注入，也早于@postConstructor
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void initByInjection() {
        System.out.println("beanFactory == applicationContext " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext " + (resourceLoader == applicationContext));
        System.out.println("applicationEventPublisher == applicationContext " + (applicationEventPublisher == applicationContext));
    }

    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class （配置类） 也是spring bean
        applicationContext.register(DependencySourceDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        //关闭spring 应用上下文
        applicationContext.close();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        getBean(BeanFactory.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationContext.class);
        getBean(ApplicationEventPublisher.class);

    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型"+beanType+" 无法在beanFactory中查找");
        }
        return null;
    }

    @Override
    public void destroy() throws Exception {

    }
}
