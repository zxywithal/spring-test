package org.think.in.spring.conversion;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.stereotype.Component;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * 将StringToPropertiesPropertyEditor 注册到spring容器中，
 * 且指定只用于User类的context属性转换
 * 自定义 {@link PropertyEditorRegistrar} 实现
 * @see PropertyEditorRegistrar
 */
//将注册器声明为bean
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        //1.通用类型转换
        //2. javabean 属性类型转换
        registry.registerCustomEditor(User.class,"context",new StringToPropertiesPropertyEditor());
    }
}
