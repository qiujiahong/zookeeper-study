package com.nick.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class RunProvider {
    public static void main(String[] args) throws IOException {
        //        创建spring容器
        ApplicationContext ac  = new ClassPathXmlApplicationContext("spring-dubbo-provider.xml");
        //启动spring容器
        ((ClassPathXmlApplicationContext)ac).start();
        //将当前主现场阻塞
        System.in.read();
    }
}
