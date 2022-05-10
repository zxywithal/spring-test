package org.think.in.spring.conversion;

import java.beans.PropertyEditor;
import java.util.Properties;

/**
 * {@link PropertyEditor} 示例
 */
public class PropertyEditorDemo {
    public static void main(String[] args) {
        // 模拟spring Framework 操作
        // 有一段文本 name=张潇赟
        String text = "name=张潇赟";
        StringToPropertiesPropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        //传递String数据
        propertyEditor.setAsText(text);
        //调用getValue方法获取参数值
        Properties value = (Properties) propertyEditor.getValue();

        System.out.println(value);

    }
}
