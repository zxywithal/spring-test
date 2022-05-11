package org.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * spring 事件示例
 * {@link ApplicationListener}
 * @see ApplicationListener
 */
public class ApplicationListenerDemo {
    public static void main(String[] args) {

        GenericApplicationContext ctx = new GenericApplicationContext();

        //向spring 上下文中注册事件
        ctx.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                //event 有可能是ContextRefreshedEvent ContextClosedEvent
                //可以根据event 的类型自定义逻辑
                System.out.println("接受到spring事件:"+event);
            }
        });
        //启动上下文
        ctx.refresh();
        //关闭
        ctx.close();
    }
}
