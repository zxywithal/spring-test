package org.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步事件处理示例
 * 通过接口设置 异步处理线程池
 * simpleApplicationEventMulticaster 在上下文中唯一，如果设置为异步则整个上下文的事件处理都是异步模式
 * @see ApplicationEventMulticaster
 * @see SimpleApplicationEventMulticaster
 * @see ExecutorService
 */
public class AsyncEventHandlerDemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new GenericApplicationContext();
        //注册监听
        ctx.addApplicationListener(new ApplicationListener<MySpringEvent>() {
            @Override
            public void onApplicationEvent(MySpringEvent event) {
                System.out.printf("[线程%s] 监听到事件 %s \n",Thread.currentThread().getName(),event);

            }
        });
        ctx.refresh();//初始化ApplicationEventMulticaster
        //依赖查找ApplicationEventMulticaster
        ApplicationEventMulticaster eventMulticaster =
                ctx.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
        //判断当前ApplicationEventMulticaster 是否为SimpleApplicationEventMulticaster
        if (eventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) eventMulticaster;
            //自定义线程名称
            ExecutorService executorService =
                    Executors.newSingleThreadExecutor(new CustomizableThreadFactory("zxy-spring-event-thread-pool"));
            //切换执行事件的的线程  异步执行
            simpleApplicationEventMulticaster.setTaskExecutor(executorService);
            //添加 关闭事件 关闭线程池
            simpleApplicationEventMulticaster.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
                @Override
                public void onApplicationEvent(ContextClosedEvent event) {
                    //防止事件传播执行多次，
                    if (!executorService.isShutdown()) {
                        executorService.shutdown();
                    }
                }
            });
            simpleApplicationEventMulticaster.addApplicationListener(new ApplicationListener<MySpringEvent>() {
                @Override
                public void onApplicationEvent(MySpringEvent event) {
                    throw new RuntimeException("演示异常处理");
                }
            });
            simpleApplicationEventMulticaster.setErrorHandler(t -> {
                System.out.println("spring 事件异常，原因"+t.getMessage());
                t.printStackTrace();
            });

        }
        //发布事件
        ctx.publishEvent(new MySpringEvent("Hello,World"));
        ctx.close();
    }
}
