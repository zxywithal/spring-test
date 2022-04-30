package org.think.in.spring.dependency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link org.springframework.beans.factory.BeanCreationException} 示例
 */
public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册BeanDefinition
//        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
//        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

        applicationContext.register(BeanCreationExceptionDemo.class);

        //启动应用上下文
        applicationContext.refresh();

        applicationContext.close();
    }

    @Bean
    public POJO bean() {
        return new POJO();
    }

    static class  POJO implements InitializingBean{

        //CommonAnnotationBeanPostProcessor
//        @PostConstruct
//        public void init() throws Throwable {
//            throw new Throwable("init : for purpose");
//        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet : for purpose");
        }
    }

}
