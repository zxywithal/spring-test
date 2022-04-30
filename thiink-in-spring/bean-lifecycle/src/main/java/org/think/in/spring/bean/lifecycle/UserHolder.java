package org.think.in.spring.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.think.in.spring.ioc.overview.domain.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.StringJoiner;

public class UserHolder implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, EnvironmentAware
        , InitializingBean, SmartInitializingSingleton, DisposableBean {
    private final User user;
    private Integer number;
    private String description;
    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private String beanName;
    private Environment environment;

    /**
     * 依赖于注解驱动
     * 当前场景：BeanFactory
     */
    @PostConstruct
    public void initialization() {
        //post process before initialization v3->v4
        this.description = "The user holder v4";
        System.out.println("initialization() "+description);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //post process before initialization v4->v5
        this.description = "The user holder v5";
        System.out.println("afterPropertiesSet() "+description);
    }

    public void init() throws Exception {
        //post process before initialization v5->v6
        this.description = "The user holder v6";
        System.out.println("init() "+description);
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserHolder.class.getSimpleName() + "[", "]")
                .add("user=" + user)
                .add("number=" + number)
                .add("description='" + description + "'")
                .add("beanName='" + beanName + "'")
                .add("environment='" + environment + "'")
                .toString();
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    @Override
    public void afterSingletonsInstantiated() {
        this.description = "The user holder v8";
        System.out.println("afterSingletonsInstantiated () "+description);
    }

    @PreDestroy
    public void PreDestroy() {
        this.description = "The user holder v10";
        System.out.println("PreDestroy() "+this.description);
    }

    @Override
    public void destroy() throws Exception {
        this.description = "The user holder v11";
        System.out.println("destroy() "+this.description);
    }

    public void doDestroy() throws Exception {
        this.description = "The user holder v12";
        System.out.println("destroy() "+this.description);
    }
}
