package org.think.in.spring.annotation.profile;


import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * {@link Profile}
 * @see Environment#getActiveProfiles()
 */
@Configuration
public class ProfileDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ProfileDemo.class);
        ConfigurableEnvironment environment = ctx.getEnvironment();
        //设置默认的profile 为odd，被激活的profile为even
        //被激活优先级大于默认，
        environment.setDefaultProfiles("odd");
        environment.setActiveProfiles("even");
        ctx.refresh();
        Integer number = ctx.getBean("number", Integer.class);
        System.out.println(number);
        ctx.close();
    }

    /**
     * 两个bean的名字设置为相同"number"
     * 但是profile 一个为odd 另一个为even
     * spring 容器中必须有激活或者默认的profile，否则找不到对应的bean
     * @return
     */
    @Bean(name = "number")
    @Profile("odd") //奇数
    public Integer odd(){
        return 1;
    }

    @Bean(name = "number")
//    @Profile("even") //偶数
    //用Conditional 代替profile
    @Conditional(EvenProfileCondition.class)
    public Integer even(){
        return 2;
    }
}
