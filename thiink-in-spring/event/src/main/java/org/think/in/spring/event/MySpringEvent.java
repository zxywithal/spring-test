package org.think.in.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 */
public class MySpringEvent extends ApplicationEvent {
    public MySpringEvent(String message) {
        super(message);
    }



}
