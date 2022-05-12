package org.think.in.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 注解方式实现事件异步执行
 * 通过taskExecutor返回异步处理线程池
 *
 * @see ExecutorService
 */
@EnableAsync
public class AnnotatedAsyncEventHandlerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        //注册配置类
        ctx.register(AnnotatedAsyncEventHandlerDemo.class);

        ctx.refresh();//初始化ApplicationEventMulticaster

        //发布事件
        ctx.publishEvent(new MySpringEvent("Hello,World"));
        ctx.close();
    }

    @Async
    @EventListener
    public void onEvent(MySpringEvent event) {
        System.out.printf("[线程%s] onEvent 方法监听到事件 %s \n", Thread.currentThread().getName(), event);
    }

    /**
     * 方法名称不能变，spring会自动获取taskExecutor 作为线程池调用
     * @return
     */
    @Bean
    public ExecutorService taskExecutor() {
        return Executors.newSingleThreadExecutor(new CustomizableThreadFactory("annotated-zxy-spring-event-thread-pool-"));
    }
}
