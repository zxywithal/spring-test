package org.think.in.spring.ioc.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认{@link UserFactory}实现
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void init() {
        System.out.println("init ……");
    }

    public void annotationInitMethod() {
        System.out.println("annotationInitMethod ……");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet ……");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy ……");
    }
    public void doDestroy() {
        System.out.println("自定义 doDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean 接口");
    }
}
