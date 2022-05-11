package org.think.in.spring.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * {@link java.util.Observer} 示例
 * java api 观察着模式示例
 */
public class ObserverDemo {
    public static void main(String[] args) {
        Observable observable = new EventObservable();
        //添加监听者
        EventObserver eventObserver = new EventObserver();
        observable.addObserver(eventObserver);
        //发布事件
        observable.notifyObservers("Hello,World");

    }

    static class EventObservable extends Observable{
        @Override
        public synchronized void setChanged() {
            super.setChanged();
        }

        @Override
        public void notifyObservers(Object arg) {
            //Observable 的逻辑是必须调用setChanged 才会继续后面的逻辑
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }
    }
    /**
     * 观察者对象
     */
    static class EventObserver implements Observer,EventListener {
        /**
         *
         * @param o
         * @param arg  notifyObservers 方法传递的对象
         */
        @Override
        public void update(Observable o, Object arg) {
            System.out.println("收到事件:"+arg);
        }
    }
}
