package org.think.in.spring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HelloWorld Configuration Class
 */
@Configuration
public class HelloWorldConfiguration {
    @Bean
    public String helloWorld(){
        return "Hello World";
    }
}