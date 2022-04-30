package org.think.in.spring.ioc.bean.definition;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.think.in.spring.ioc.overview.domain.User;

public class BeanDefinitionCreationDemo {


    public static void main(String[] args) {
        //1.通过BeanDefinitionBuilder 定义bean
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        definitionBuilder.addPropertyValue("id", 1);
        definitionBuilder.addPropertyValue("name", "zxy");
        //获取beandefinition
        BeanDefinition beanDefinition = definitionBuilder.getBeanDefinition();

        //通过AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        //设置bean类型
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("id", 1).add("name", "zxy");
        genericBeanDefinition.setPropertyValues(propertyValues);

    }
}
