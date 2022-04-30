package org.think.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link NoUniqueBeanDefinitionException} 示例
 */
public class NoUniqueBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        try {
            String bean = applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf("NoUniqueBeanDefinitionException 类型 %s 共 %s 个 错误信息 %s",
                    String.class.getName(),
                    e.getNumberOfBeansFound(),
                    e.getMessage());
        }

        applicationContext.close();
    }

    @Bean
    public String bean1() {
        return "bean1";
    }
    @Bean
    public String bean2() {
        return "bean2";
    }
}
