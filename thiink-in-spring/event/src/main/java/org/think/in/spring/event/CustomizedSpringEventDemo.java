package org.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 自定义spring event 为将业务代码解耦
 */
public class CustomizedSpringEventDemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new GenericApplicationContext();
        //注册监听
        ctx.addApplicationListener(new ApplicationListener<MySpringEvent>() {
            @Override
            public void onApplicationEvent(MySpringEvent event) {
                System.out.printf("[线程%s] 监听到事件 %s \n",Thread.currentThread().getName(),event);

            }
        });
        ctx.refresh();
        //发布事件
        ctx.publishEvent(new MySpringEvent("Hello,World"));
        ctx.close();
    }
}
