package org.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * spring 事件示例
 * {@link ApplicationListener}
 * @see ApplicationListener
 */
//开启异步
@EnableAsync
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {
    public static void main(String[] args) {

        //GenericApplicationContext ctx = new GenericApplicationContext();
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationListenerDemo.class);

        //1.基于spring接口向spring 上下文中注册事件监听
        //a 方法基于AbstractApplicationContext#addApplicationListener
        ctx.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                //event 有可能是ContextRefreshedEvent ContextClosedEvent
                //可以根据event 的类型自定义逻辑
                print("接受到spring事件:"+event);
            }
        });
        //b 方法基于ApplicationListener 注册为bean
        ctx.register(MyApplicationListener.class);
        //启动上下文
        ctx.refresh();
        //关闭
        ctx.close();
    }

    //注入事件发布器
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        //发布事件
        applicationEventPublisher.publishEvent(new ApplicationEvent("Hello,world") {});

        applicationEventPublisher.publishEvent("lllllllll");

    }

    //实现接口,通过泛型参数来指定具体的事件类型
    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent>{
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            print("MyApplicationListener 接受到spring事件:"+event);
        }
    }
    //2.基于注解，向spring注册事件监听,注解的方式可以有多个重载方法，用来将业务逻辑分流
    //接受所有的内置事件
    @EventListener
    public void onApplicationEvent(ApplicationEvent event) {
        print("@EventListener - 接收到spring事件:"+event);
    }
    //只接收ContextRefreshedEvent事件
    @EventListener
    //Order 控制顺序 ，数值越小越靠前
    @Order(1)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        print("@EventListener 1111 - 接收到spring - ContextRefreshedEvent事件:"+event);
    }

    @EventListener
    @Order(0)
    public void onApplicationEvent1(ContextRefreshedEvent event) {
        print("@EventListener  2222 - 接收到spring - ContextRefreshedEvent事件:" + event);
    }

    @EventListener
    //异步
    @Async
    public void onApplicationEventAsync(ContextRefreshedEvent event) {
        print("@EventListener- Async - 接收到spring - ContextRefreshedEvent事件:"+event);
    }
    //只接收ContextClosedEvent 事件
    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        print("@EventListener - 接收到spring - ContextClosedEvent事件:"+event);
    }

    public static void print(Object printAble) {
        System.out.printf("[线程:%s]:%s \n",Thread.currentThread().getName(),printAble);
    }

}
