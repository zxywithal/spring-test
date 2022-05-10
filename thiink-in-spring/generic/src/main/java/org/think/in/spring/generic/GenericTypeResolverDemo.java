package org.think.in.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * spring {@link GenericTypeResolver} 示例
 * @see GenericTypeResolver
 */
public class GenericTypeResolverDemo {
    public static void main(String[] args) throws NoSuchMethodException {
        //String 是 <Comparable> 具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, Comparable.class,"getString");
        //ArrayList<Object> 是List泛型参数类型具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class,"getList");
        //StringList 是List泛型参数类型具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class,"getStringList");
        //具备ParameterizedType返回，否则null


        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
    }
    //泛型参数具体化后字节码中才有记录
    public static List<Object> getList(){
        return null;
    }
    public static String getString(){
        return null;
    }

    public static StringList getStringList(){
        return null;
    }

    private static void displayReturnTypeGenericInfo(Class<?> containingClass,Class<?> genericIfc, String methodName, Class... argumentTypes) throws NoSuchMethodException {
        Method method = containingClass.getMethod(methodName);

        //获取method的return type
        Class<?> returnType = GenericTypeResolver.resolveReturnType(method, containingClass);
        //常规类作为返回值
        System.out.printf("GenericTypeResolver.resolveReturnType(%s,%s)=%s \n",methodName,containingClass.getSimpleName(),returnType);
        //常规类型不具备泛型参数类型 returnTypeArgument List<E>
        Class<?> returnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, genericIfc);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s,%s)=%s \n",methodName,containingClass.getSimpleName(),returnTypeArgument);

    }

    //泛型参数具体化,在字节码中有记录
    static class StringList extends ArrayList<String> {

    }
}
