package org.think.in.spring.conversion;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * spring 自定义{@link java.beans.PropertyEditor} 示例
 */
public class SpringCustomizedPropertyEditorDemo {
    public static void main(String[] args) {
        //创建beanFactory 容器
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/property-editors-context.xml");
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        //关闭spring上下文
        applicationContext.close();
    }
}
