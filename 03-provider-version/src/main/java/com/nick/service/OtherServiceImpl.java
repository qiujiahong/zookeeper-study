package com.nick.service;

public class OtherServiceImpl implements SomeService {
    @Override
    public String hello(String name) {
        System.out.println("======执行OtherServiceImpl,"+ name);
        return name;
    }
}
