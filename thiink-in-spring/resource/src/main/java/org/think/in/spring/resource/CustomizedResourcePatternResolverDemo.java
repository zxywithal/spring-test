package org.think.in.spring.resource;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.think.in.spring.resource.util.ResourceUtils;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * 地址统配示例
 * 自定义 {@link org.springframework.core.io.support.ResourcePatternResolver} 示例
 * @see org.springframework.core.io.support.ResourcePatternResolver
 * @see org.springframework.core.io.support.PathMatchingResourcePatternResolver
 * @see java.nio.file.PathMatcher
 */
public class CustomizedResourcePatternResolverDemo {
    public static void main(String[] args) throws IOException {
        //读取当前package 对应的所有.java 文件
        //*.java
        //读取当前类的源码
        String currentPackagePath = "/"+System.getProperty("user.dir")+"/thiink-in-spring/resource/src/main/java/org/think/in/spring/resource/";
        //统配地址
        String locationPattern = currentPackagePath + "*.java";
        //创建地址统配解析器
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
        final Resource[] resources = resourcePatternResolver.getResources(locationPattern);
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);

    }
}
