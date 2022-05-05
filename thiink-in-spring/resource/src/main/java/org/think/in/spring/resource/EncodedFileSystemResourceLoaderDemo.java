package org.think.in.spring.resource;


import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * 带有字符编码的{@link org.springframework.core.io.FileSystemResourceLoader} 示例
 * @see org.springframework.core.io.FileSystemResourceLoader
 * @see FileSystemResource
 * @see EncodedResource
 */
public class EncodedFileSystemResourceLoaderDemo {
    public static void main(String[] args) throws IOException {
        //读取当前类的源码
        String currentJavaFilePath = "/"+System.getProperty("user.dir")+"/thiink-in-spring/resource/src/main/java/org/think/in/spring/resource/EncodedFileSystemResourceLoaderDemo.java";
        File currentJavaFile = new File(currentJavaFilePath);
        //新建FileSystemResourceLoader
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        //将文件加载成为resource
        Resource resource = resourceLoader.getResource(currentJavaFilePath);
        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");
        //获取字符输入流
        try(Reader reader = encodedResource.getReader()){
            //利用apache的io工具类将reader输出
            System.out.println(IOUtils.toString(reader));
        }
    }
}
