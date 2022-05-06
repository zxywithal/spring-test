package org.think.in.spring.validation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Spring Bean validation 整合示例
 * @see Validator
 * @see LocalValidatorFactoryBean
 */
public class SpringBeanValidationDemo {
    public static void main(String[] args) {
        //配置xml文件启动spring容器
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-validation-context.xml");

//        applicationContext.refresh();

//        Validator validator = applicationContext.getBean(Validator.class);
//        System.out.println(validator instanceof LocalValidatorFactoryBean);

        UserProcess userProcess = applicationContext.getBean(UserProcess.class);
        userProcess.process(new User());

        //关闭上下文
        applicationContext.close();
    }

    @Component
    //Validated 必须要有
    @Validated
    static class UserProcess{
        public void process(@Valid User user) {
            System.out.println(user);
        }
    }

    static class User{
        @NotNull
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
