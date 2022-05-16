package org.think.in.spring.application.lifecycle;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.support.GenericApplicationContext;

public class LifeCycleDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        //注册LifeCycle
        context.registerBeanDefinition("myLifeCycle", BeanDefinitionBuilder.rootBeanDefinition(MyLifeCycle.class).getBeanDefinition());
        context.refresh();
//        context.start();
//        context.stop();
        context.close();
    }
}
