package org.think.in.spring.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

/**
 * String -> Properties {@link PropertyEditor}
 * 自定义类型转换示例
 * spring已经有了string 转换为properties的类型转换器，
 * 这里只是简单的演示类型转换器的实现方式
 * @see PropertyEditorSupport
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport implements PropertyEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        //将string转换为Properties
        Properties properties = new Properties();
        try {
//            properties.load(new StringReader(text));
            properties.load(new ByteArrayInputStream(text.getBytes(StandardCharsets.ISO_8859_1)));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        //临时存储Properties临时对象
        setValue(properties);
        //next 获取临时Properties 对象  getValue

    }

    @Override
    public Object getValue() {
        Properties properties = (Properties)getValue();
        StringBuilder textBuilder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            textBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append(System.getProperty("line.separator"));
        }
        return textBuilder.toString();
    }
}
