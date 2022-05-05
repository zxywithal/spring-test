package org.think.in.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.think.in.spring.resource.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * 注入 {@link ResourceLoader} 示例
 * //方法1 利用@Autowired 注入
 * //方法2 实现ResourceLoaderAware 接口
 * //方法3 注入ApplicationContext
 * 以上三种获取到的对象是同一个
 * @see AnnotationConfigApplicationContext
 * @see ResourceLoader
 * @see Resource
 */
public class InjectingResourceLoaderDemo implements ResourceLoaderAware {

    //方法1
    @Autowired
    private ResourceLoader autowiredResourceLoader;

    private ResourceLoader resourceLoaderAware;

    //方法3
    @Autowired
    private ApplicationContext applicationContext;



    @PostConstruct
    public void init() {
        System.out.println("autowiredResourceLoader == resourceLoaderAwar : "+(autowiredResourceLoader == resourceLoaderAware));
        System.out.println("autowiredResourceLoader == applicationContext : "+(autowiredResourceLoader == applicationContext));
    }
    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class （配置类） 也是spring bean
        applicationContext.register(InjectingResourceLoaderDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        //关闭spring 应用上下文
        applicationContext.close();
    }
    //方法2
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoaderAware = resourceLoader;
    }
}
