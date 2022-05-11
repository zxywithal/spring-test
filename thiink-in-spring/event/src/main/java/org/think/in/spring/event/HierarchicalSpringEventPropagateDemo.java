package org.think.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 层次性事件传播示例
 * MyListener 被注册到parentApplication 和currentApplication
 * currentApplication 上下文 会触发两次ContextRefreshedEvent 事件
 *
 * 父传子
 */
public class HierarchicalSpringEventPropagateDemo {
    public static void main(String[] args) {
        //1.创建parent spring上下文
        AnnotationConfigApplicationContext parentApplication = new AnnotationConfigApplicationContext();
        parentApplication.setId("parentApplication");
        //注册MyListener到parentApplication
        parentApplication.register(MyListener.class);
        //2.创建current spring上下文
        AnnotationConfigApplicationContext currentApplication = new AnnotationConfigApplicationContext();
        currentApplication.setId("currentApplication");
        //3.将current->parent
        currentApplication.setParent(parentApplication);
//        currentApplication.register(MyListener.class);

        //启动parent
        parentApplication.refresh();
        //启动current 发布 ContextRefreshedEvent
        currentApplication.refresh();

        //关闭  触发ContextClosedEvent
        parentApplication.close();
        currentApplication.close();

    }

    /**
     * 涉及到层次性上下文的时候由于事件传播的特性，onApplicationEvent会多次调用
     * 可以采用静态状态属性解决这个问题
     */
    static class MyListener implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.printf("监听到spring 应用上下文【id:%s】的ContextRefreshedEvent \n", event.getApplicationContext().getId());
        }
    }

}
