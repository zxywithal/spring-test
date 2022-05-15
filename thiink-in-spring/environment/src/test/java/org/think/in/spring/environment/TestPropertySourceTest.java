package org.think.in.spring.environment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link TestPropertySource} 示例
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestPropertySourceTest.class) //Spring 注解驱动测试注解
@TestPropertySource(
        properties = {"user.name=张潇赟"},  // propertySourceName = Inlined Test Properties  这个级别最高
        locations = {"classpath:META-INF/test.properties"}//class path resource [META-INF/test.properties]
)
public class TestPropertySourceTest {
    @Value("${user.name}")
    private String userName;
    @Autowired
    private ConfigurableEnvironment environment;

    @Test
    public void testUserName(){
        Assert.assertEquals("张潇赟", userName);
        for (PropertySource<?> propertySource : environment.getPropertySources()) {
            System.out.println(propertySource.getName()+": user.name="+propertySource.getProperty("user.name"));
        }
    }
}
