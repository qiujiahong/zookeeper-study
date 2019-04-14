package com.nick.service;

public class SomeServiceImpl implements SomeService {
    @Override
    public String hello(String name) {
        System.out.println("dubbo word welcome you,"+ name);
        return name;
    }
}
