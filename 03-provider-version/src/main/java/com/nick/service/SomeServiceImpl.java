package com.nick.service;

public class SomeServiceImpl implements SomeService {
    @Override
    public String hello(String name) {
        System.out.println("======执行SomeServiceImpl,"+ name);
        return name;
    }
}
