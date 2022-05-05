package org.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * "users.xsd" {@link org.springframework.beans.factory.xml.NamespaceHandler} 实现
 */
public class UsersNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        //将user元素注册对应的BeanDefinitionParser 实现
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());

    }
}
