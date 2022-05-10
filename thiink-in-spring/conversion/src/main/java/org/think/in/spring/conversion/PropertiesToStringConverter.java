package org.think.in.spring.conversion;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * {@link Properties} -> {@link String} {@link ConditionalGenericConverter} 实现
 * 模拟将Properties对象转成string 类型，仅限于User对象的contextAsText属性
 * @see ConditionalGenericConverter
 */
public class PropertiesToStringConverter implements ConditionalGenericConverter {
    //matches 方法负责判断sourceType 和targetType 是否为期望的java类型
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        boolean sourceEqual = Properties.class.equals(sourceType.getObjectType());
        boolean targetEqual = String.class.equals(targetType.getObjectType());

        return sourceEqual && targetEqual;
    }

    //负责返回sourceType 和targetType 的类型的ConvertiblePair
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Properties.class,String.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Properties properties = (Properties)source;
        StringBuilder textBuilder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            textBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append(System.getProperty("line.separator"));
        }
        return textBuilder.toString();
    }
}
