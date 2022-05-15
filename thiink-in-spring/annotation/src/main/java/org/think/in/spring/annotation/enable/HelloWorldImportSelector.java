package org.think.in.spring.annotation.enable;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Hello World {@link ImportSelector}
 */
public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"org.think.in.spring.annotation.enable.HelloWorldConfiguration"};
    }
}
