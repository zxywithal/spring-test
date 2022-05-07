package org.think.in.spring.data.binding;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.think.in.spring.ioc.overview.domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据绑定特性示例
 * @see DataBinder
 * @see PropertyValues
 * @see MutablePropertyValues
 */
public class DataBinderDemo {
    public static void main(String[] args) {
        User user = new User();
        DataBinder dataBinder = new DataBinder(user, "user");
        //创建PropertyValues
        Map<String, Object> source = new HashMap<>();
        source.put("id", 1);
        source.put("name", "张潇赟");
        //PropertyValues 存在User中不存在属性值
        //DataBinder 特性1 忽略未知的属性
        source.put("age", 18);
        //PropertyValues 存在一个嵌套属性，比如company.name
        //DataBinder 特性2 支持嵌套属性
        source.put("company.name", "mry");

        PropertyValues propertyValues = new MutablePropertyValues(source);

        //1.调整 IgnoreUnknownFields（默认true） 是否忽略位置属性参数，IgnoreUnknownFields字段默认是true
//        dataBinder.setIgnoreUnknownFields(Boolean.FALSE);
        //2.调整AutoGrowNestedPaths(默认true） 是否自动增加嵌套路径
//        dataBinder.setAutoGrowNestedPaths(Boolean.FALSE);
        //3.调整 IgnoreInvalidFields（默认false） 是否忽略非法字段，需要调整
        dataBinder.setIgnoreInvalidFields(Boolean.TRUE);
        //4.设置必须字段
        dataBinder.setRequiredFields("id", "name", "city");

        dataBinder.bind(propertyValues);

        //输出user内容
        System.out.println(user);
        //4.获取绑定结果（结果包含错误文案 code，不会抛出异常）
        BindingResult bindingResult = dataBinder.getBindingResult();
        System.out.println(bindingResult);
    }
}
