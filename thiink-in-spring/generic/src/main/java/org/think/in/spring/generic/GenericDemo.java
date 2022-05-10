package org.think.in.spring.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * java 泛型示例
 */
public class GenericDemo {
    public static void main(String[] args) {
        Collection<String> list = new ArrayList<>();
        list.add("Mello");
        list.add("world");

        //泛型擦写
        Collection temp = list;
        //欺骗编译器，不影响运行时
        temp.add(1);
        System.out.println(list);
    }
}
