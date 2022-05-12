package org.think.in.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ApplicationEventPublisher 注入示例
 * 4种方式
 * 1.ApplicationEventPublisherAware 接口注入
 * 2.ApplicationContextAware 接口注入，因为application 接口就是ApplicationEventPublisher
 * 3.@Autowired 注入applicationContext
 * 4.@Autowired 注入 applicationEventPublisher
 * @see ApplicationEventPublisherAware
 * @see ApplicationContextAware
 *
 */
public class InjectionApplicationEventPublisherDemo implements ApplicationEventPublisherAware, ApplicationContextAware {
    //注入
    @Autowired
    private ApplicationContext applicationContext;
    //注入
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init(){
        //#3
        applicationEventPublisher.publishEvent(new MySpringEvent("The event from Autowired applicationEventPublisher"));
        //#4
        applicationContext.publishEvent(new MySpringEvent("The event from Autowired applicationContext"));
    }

    //ApplicationContextAware  接口 #1
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //ApplicationContext 就是ApplicationEventPublisher
        applicationContext.publishEvent(new MySpringEvent("The event from ApplicationContextAware"));
    }
    //ApplicationEventPublisherAware 接口 #2
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new MySpringEvent("The event from ApplicationEventPublisherAware"));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        //注册配置类
        ctx.register(InjectionApplicationEventPublisherDemo.class);
        //注册监听
        ctx.addApplicationListener(new ApplicationListener<MySpringEvent>() {
            @Override
            public void onApplicationEvent(MySpringEvent event) {
                System.out.printf("[线程%s] 监听到事件 %s \n",Thread.currentThread().getName(),event);

            }
        });
        ctx.refresh();

        ctx.close();
    }
}
