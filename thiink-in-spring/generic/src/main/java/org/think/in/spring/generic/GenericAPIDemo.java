package org.think.in.spring.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * java 泛型api demo
 */
public class GenericAPIDemo {
    public static void main(String[] args) {
        //原生类型 int long float
        Class intClass = int.class;
        //数组类型
        Class objectArrayClass = Object[].class;
        //原始类型 raw type : java.lang.String
        Class rawClass = String.class;
        //泛型参数类型 ParameterizedType
        ParameterizedType parameterizedType = (ParameterizedType)ArrayList.class.getGenericSuperclass();
        //java.util.AbstractList<E>
        System.out.println(parameterizedType);
        //parameterizedType.getRawType = java.util.AbstractList
        System.out.println(parameterizedType.getRawType());
        //<E>
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        Stream.of(typeArguments).map(type -> {
            return TypeVariable.class.cast(type);
        }).forEach(System.out::println);
        //泛型类型变量 Type variable

    }
}
