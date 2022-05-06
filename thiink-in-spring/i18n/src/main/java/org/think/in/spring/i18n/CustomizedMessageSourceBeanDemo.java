package org.think.in.spring.i18n;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * spring boot 场景下自定义 {@link MessageSource} Bean
 *
 * @see MessageSource
 * @see MessageSourceAutoConfiguration
 * @see ReloadableResourceBundleMessageSource
 */
public class CustomizedMessageSourceBeanDemo {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                new SpringApplicationBuilder(CustomizedMessageSourceBeanDemo.class)
                        .web(WebApplicationType.NONE)
                        .run(args);
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        if (beanFactory.containsBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)) {
            //如果包含messageSource对象
            //获取BeanDefinition对象
            System.out.println(beanFactory.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME));
            //查找 MessageSource 的BeanDefinition
            MessageSource messageSource = applicationContext.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
            System.out.println(messageSource);
        }
        //关闭应用上下文
        applicationContext.close();
    }
}
