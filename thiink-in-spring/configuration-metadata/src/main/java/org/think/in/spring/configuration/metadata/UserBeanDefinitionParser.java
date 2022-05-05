package org.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.config.FieldRetrievingFactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.think.in.spring.ioc.overview.domain.User;
import org.w3c.dom.Element;

/**
 * user 元素的{@link org.springframework.beans.factory.xml.BeanDefinitionParser} 实现
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        String id = setPropertyValue(element, builder, "id");
        String name = setPropertyValue(element, builder, "name");
        setPropertyValue(element, builder, "city");
    }

    private String setPropertyValue(Element element, BeanDefinitionBuilder builder, String attributeName) {
        String attributeValue = element.getAttribute(attributeName);
        if (StringUtils.hasText(attributeValue)) {
            //BeanDefinition 定义id属性 -> <property name="id" value="1"/>
            builder.addPropertyValue(attributeName, attributeValue);
        }
        return attributeValue;
    }

}
