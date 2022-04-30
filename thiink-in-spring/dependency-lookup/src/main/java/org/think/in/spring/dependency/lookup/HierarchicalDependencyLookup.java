package org.think.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性 依赖查找示例
 * @since
 */
public class HierarchicalDependencyLookup {
    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyLookup.class);
        //1. 获取HierarchicalBeanFactory<-ConfigurableBeanFactory<-ConfigurableListableBeanFactory

        ConfigurableListableBeanFactory configurableListableBeanFactory = applicationContext.getBeanFactory();
        System.out.println("当前BeanFactory的parent BeanFactory :" + configurableListableBeanFactory.getParentBeanFactory());
        //2. 设置Parent BeanFactory
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        configurableListableBeanFactory.setParentBeanFactory(parentBeanFactory);
        //确定，获取parentBeanFactory 只能通过configurableListableBeanFactory 获取，applicationContext 无法获取
        System.out.println("parentBeanFactory :"+configurableListableBeanFactory.getParentBeanFactory());

        //启动应用上下文
        applicationContext.refresh();
        displayLocalBean(applicationContext,"user");
        displayLocalBean(parentBeanFactory,"user");

        displayBean(applicationContext,"user");
        displayBean(parentBeanFactory,"user");
        applicationContext.close();
    }

    /**
     * 递归校验是否包含对应的bean
     * @param beanFactory
     * @param beanName
     * @return
     */
    private static boolean containBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            //如果parent BeanFactory 是HierarchicalBeanFactory 继续找他的parent
            HierarchicalBeanFactory hierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            return containBean(hierarchicalBeanFactory, beanName);
        } else {
            //如果不是 用当前对象校验是否包含对应beanName
            return beanFactory.containsBean(beanName);
        }
    }


    private static void displayBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含bean[name:%s] : %s\n"
                ,beanFactory
                ,beanName
                ,containBean(beanFactory,beanName));
    }

    private static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含local bean[name:%s] : %s\n"
                ,beanFactory
                ,beanName
                ,beanFactory.containsLocalBean(beanName));
    }

    public static HierarchicalBeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        return factory;
    }
}
