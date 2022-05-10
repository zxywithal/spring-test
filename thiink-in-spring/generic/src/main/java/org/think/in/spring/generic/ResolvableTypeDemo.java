package org.think.in.spring.generic;

import org.springframework.core.ResolvableType;

/**
 * {@link ResolvableType} Demo
 */
public class ResolvableTypeDemo {
    public static void main(String[] args) {
        //工厂创建
        //StringList <- ArrayList <- AbstractList <- AbstractCollection
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);
        //ArrayList
        System.out.println(resolvableType.getSuperType());
        //AbstractList
        System.out.println(resolvableType.getSuperType().getSuperType());
        //AbstractCollection
        System.out.println(resolvableType.getSuperType().getSuperType().getSuperType());
        //Object
        System.out.println(resolvableType.getSuperType().getSuperType().getSuperType().getSuperType());
        //获取raw type  Collection
        System.out.println(resolvableType.asCollection().resolve());
        //原始类型的泛型类型
        System.out.println(resolvableType.asCollection().resolveGeneric(0));

    }
}
