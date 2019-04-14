package com.nick.run;

import com.nick.service.SomeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunConsumer  {

    public static void main(String[] args) {

        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-dubbo-consumer.xml");
        SomeService service = (SomeService)ac.getBean("someService");

//        System.out.println(service.hello("Tom"));
//        System.out.println(service.hello("Tom"));
//        System.out.println(service.hello("Jerry"));

        for(int i =1 ;i<=1000;i++){
            service.hello("i=="+i);
        }
        //降低一个缓存挤出去
        System.out.println(service.hello("Jerry"));
        System.out.println(service.hello("i==1"));//从提供则缓存获取
        System.out.println(service.hello("i==3"));//从缓存输出



    }
}
