package org.think.in.spring.environment;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * 通过xml配置properties资源文件
 */
public class PropertyPlaceholderConfigureDemo {
    public static void main(String[] args) {
        //加载配置文件并启动
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/placeholders-resolver.xml");
        User user = ctx.getBean("user", User.class);
        System.out.println(user);
        ctx.close();

    }
}
