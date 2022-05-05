package org.think.in.spring.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.think.in.spring.resource.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * 注入 {@link Resource} 示例
 * 使用@Value 注入resource 对象、注入系统变量
 * @see AnnotationConfigApplicationContext
 */
public class InjectingResourceDemo {

    //@Value 注入资源对象
    @Value("classpath:/META-INF/default.properties")
    private Resource defaultPropertiesResource;

    //Autowired和Value复用的是AutowiredAnnotationBeanPostProcessor类实现,所有这儿用autowired效果应该是一样的
    //@Value 利用地址统配注入多个资源
    @Value("classpath*:/META-INF/*.properties")
    private Resource[] propertiesResource;
    //注入系统变量
    @Value("${user.dir}")
    private String currentProjectRootPath;
    @PostConstruct
    public void init() {
        System.out.println(currentProjectRootPath);
        System.out.println("================");
        System.out.println(ResourceUtils.getContent(defaultPropertiesResource));
        System.out.println("================");
        Stream.of(propertiesResource).map(ResourceUtils::getContent).forEach(System.out::println);

    }
    public static void main(String[] args) {
        //创建beanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class （配置类） 也是spring bean
        applicationContext.register(InjectingResourceDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        //关闭spring 应用上下文
        applicationContext.close();
    }
}
