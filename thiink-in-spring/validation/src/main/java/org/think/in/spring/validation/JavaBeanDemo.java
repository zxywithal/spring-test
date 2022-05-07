package org.think.in.spring.validation;

import org.think.in.spring.ioc.overview.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * JavaBeans 示例
 */
public class JavaBeanDemo {
    public static void main(String[] args) throws IntrospectionException {

        BeanInfo beanInfo = Introspector.getBeanInfo(User.class,Object.class);
        //属性描述符 PropertyDescriptor
        //class属性是来源于父类java.lang.object 中的 Object#getClass（） 方法
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            System.out.println(propertyDescriptor);
        });

        //输出user定义的方法
        Stream.of(beanInfo.getMethodDescriptors()).forEach(System.out::println);

    }
}
