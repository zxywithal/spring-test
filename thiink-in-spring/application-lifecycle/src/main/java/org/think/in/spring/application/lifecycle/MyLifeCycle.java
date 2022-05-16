package org.think.in.spring.application.lifecycle;

import org.springframework.context.Lifecycle;

public class MyLifeCycle implements Lifecycle {

    private boolean running;

    @Override
    public void start() {
        this.running = true;
        System.out.println("启动中……");
    }

    @Override
    public void stop() {
        this.running = false;
        System.out.println("停止中……");
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
