package org.think.in.spring.application.lifecycle;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

/**
 * Spring Shutdown hook 线程示例
 */
public class SpringShutdownHookThreadDemo {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();
        //注册LifeCycle
        context.registerBeanDefinition("myLifeCycle", BeanDefinitionBuilder.rootBeanDefinition(MyLifeCycle.class).getBeanDefinition());
        context.refresh();
        context.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                System.out.println("[线程 %s] ContextClosedEvent处理"+Thread.currentThread().getName());

            }
        });
        //注册关闭钩子，来应对kill 命令 后不执行close方法的情况
        context.registerShutdownHook();

        System.out.println("按任意键继续,并关闭spring上下文...");
        System.in.read();
//        context.start();
//        context.stop();
        context.close();
    }
}
