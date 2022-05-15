package org.think.in.spring.annotation.enable;


import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class HelloWorldImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotatedGenericBeanDefinition annotatedBeanDefinition = new AnnotatedGenericBeanDefinition(HelloWorldConfiguration.class);

        BeanDefinitionReaderUtils.registerWithGeneratedName(annotatedBeanDefinition,registry);
    }
}
