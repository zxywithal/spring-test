package org.think.in.spring.resource;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.PathMatcher;
import org.think.in.spring.resource.util.ResourceUtils;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 地址统配示例,自定义匹配规则
 * 自定义 {@link org.springframework.core.io.support.ResourcePatternResolver} 示例
 * @see org.springframework.core.io.support.ResourcePatternResolver
 * @see org.springframework.core.io.support.PathMatchingResourcePatternResolver
 * @see org.springframework.util.PathMatcher
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
        //自定义规则解析，会将对应目录下的所有文件路径挨个调用JavaFilePathMather 确认是否匹配
        resourcePatternResolver.setPathMatcher(new JavaFilePathMather());

        final Resource[] resources = resourcePatternResolver.getResources(locationPattern);
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);

    }

    static class JavaFilePathMather implements PathMatcher {
        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }


        @Override
        public boolean match(String pattern, String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean matchStart(String pattern, String path) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String pattern, String path) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String path) {
            return null;
        }

        @Override
        public String combine(String pattern1, String pattern2) {
            return null;
        }
    }
}
