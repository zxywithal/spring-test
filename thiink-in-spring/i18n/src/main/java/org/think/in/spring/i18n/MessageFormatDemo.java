package org.think.in.spring.i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * {@link java.text.MessageFormat}
 * java 原生的MessageFormat示例
 */
public class MessageFormatDemo {
    public static void main(String[] args) {
        int planet = 7;
        String event = "a disturbance in the Force";

        String pattern = "At {1,time, } on {1,date,full}, there was {2} on planet {0,number,integer}.";
        MessageFormat messageFormat = new MessageFormat(pattern);
        String result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        //重置pattern,相当于messageformate 对象重复使用
        messageFormat.applyPattern("This is text {0}");
        System.out.println(messageFormat.format(new Object[]{"hello world"}));
        //重置local
        messageFormat.setLocale(Locale.ENGLISH);
        messageFormat.applyPattern(pattern);
        System.out.println(messageFormat.format(new Object[]{planet, new Date(), event}));
        //自定义formate
        messageFormat.setFormat(1,new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"));
        System.out.println(messageFormat.format(new Object[]{planet, new Date(), event}));

    }
}
