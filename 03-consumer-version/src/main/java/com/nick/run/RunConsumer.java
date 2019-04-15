package com.nick.run;

import com.nick.service.SomeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunConsumer  {

    public static void main(String[] args) {

        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-dubbo-consumer.xml");
        SomeService service = (SomeService)ac.getBean("service");

        System.out.println(service.hello("Tom"));



    }
}
