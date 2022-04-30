package org.think.in.spring.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;
import org.think.in.spring.ioc.overview.domain.SuperUser;
import org.think.in.spring.ioc.overview.domain.User;

/**
 * 在bean初始化后拦截，可以自定义生成一些代理对象
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    //实例化前，如果方法返回对象不为空则会跳过实例化后回调、属性赋值前回调、属性赋值、初始化前回调、初始化后回调
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
            return new SuperUser();
        }
        //保持默认的实例化
        return null;
    }

    //实例化后回调,如果方法返回false，则会被跳过当前bean后续的 属性赋值前回调、属性赋值、初始化前回调、初始化后回调
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
            //"user" 对象不允许属性赋值
            User user = User.class.cast(bean);
            user.setId(2L);
            user.setName("zzzzzz");
            return false;
        }
        return true;    //保持默认的实例化
    }

    //属性赋值之前回调，可以修改xml配置的属性值
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            MutablePropertyValues propertyValues = null;
            if (pvs instanceof MutablePropertyValues) {
                propertyValues = (MutablePropertyValues) pvs;
            } else {
                propertyValues = new MutablePropertyValues();
            }
            //等价于         <property name="number" value="1"/>
            propertyValues.addPropertyValue("number", "1");
            if (propertyValues.contains("description")) {
                propertyValues.removePropertyValue("description");
                propertyValues.addPropertyValue("description", "The user holder v2");
            }
            return propertyValues;
        }
        return null;
    }

    //初始化前回调
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = UserHolder.class.cast(bean);
            userHolder.setDescription("The user holder v3");
        }
        //superUser bean 的回调并不会进入，
        // 因为superUser bean对象的实例化前方法(postProcessBeforeInstantiation) 对返回的对象已经定制处理
        if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(bean.getClass())) {
            SuperUser superUser = SuperUser.class.cast(bean);
            superUser.setAddress("LANGFANG");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = UserHolder.class.cast(bean);
            userHolder.setDescription("The user holder v7");
            System.out.println("postProcessAfterInitialization () "+userHolder.getDescription());
        }
        return bean;
    }
}