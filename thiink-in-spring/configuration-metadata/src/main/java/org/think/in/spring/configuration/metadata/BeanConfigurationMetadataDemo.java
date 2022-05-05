package org.think.in.spring.configuration.metadata;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.util.ObjectUtils;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * bean 配置元信息示例
 */
public class BeanConfigurationMetadataDemo {
    public static void main(String[] args) {
        //BeanDefinitionBuilder 声明
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "zxy");
        //获取AbstractBeanDefinition
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //附加属性（不影响Bean实例化，属性赋值（populate），初始化）
        //attribute 为上上下文属性
        beanDefinition.setAttribute("name", "张潇赟");
        //当前BeanDefinition来自原 哪里 （辅助作用）
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //注册beanDefinition
        factory.registerBeanDefinition("user", beanDefinition);

        factory.addBeanPostProcessor(new BeanPostProcessor() {
            //初始化完成后的自定义流程，不会影响初始化过程中任何过程
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals(beanName, "user") && User.class.equals(bean.getClass())) {
                    BeanDefinition bd = factory.getBeanDefinition(beanName);
                    if (BeanConfigurationMetadataDemo.class.equals(bd.getSource())) {
                        //这活儿获取的数据应该是"张潇赟"
                        //为上上下文属性
                        String name = (String)bd.getAttribute("name");
                        User user = User.class.cast(bean);
                        user.setName(name);
                    }
                }
                return bean;
            }
        });
        User user = factory.getBean("user", User.class);
        System.out.println(user);

    }

}
