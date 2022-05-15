package org.think.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;

/**
 * 演示属性变更
 */
@Configuration
public class EnvironmentPropertySourceChangeDemo {
    //当前上下文中有两个user.name属性
    //1.PropertySource("first"){user.name=张潇赟}
    //2.PropertySource("Java System Properties"){user.name=zxy}
    @Value("${user.name}")
    private String userName;
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(EnvironmentPropertySourceChangeDemo.class);
        //在spring 启动钱，调整Environment 中的PropertySource
        ConfigurableEnvironment environment = ctx.getEnvironment();
        //获取MutablePropertySources对象
        MutablePropertySources propertySources = environment.getPropertySources();
        HashMap<String, Object> map = new HashMap<>();
        map.put("user.name", "张潇赟");
        //不具备动态刷新能力，必须在启动前执行addFirst方法，才会影响@value注入的值
        MapPropertySource source = new MapPropertySource("first", map);

        propertySources.addFirst(source);
        for (PropertySource<?> propertySource : propertySources) {
            System.out.println(propertySource.getName()+": user.name="+propertySource.getProperty("user.name"));
        }

        ctx.refresh();
        EnvironmentPropertySourceChangeDemo bean = ctx.getBean(EnvironmentPropertySourceChangeDemo.class);
        System.out.println(bean.userName);
        ctx.close();
    }
}
