package com.nick.provider.cache;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport {

    //    自动生成的key : 类名_方法名_参数值
    //    EmployeeServiceImpl_findEmployeeById_5
    //    KeyGenerator 函数式接口  jdk8新特性。
    public KeyGenerator keyGenerator(){

        return (target,method,param) ->{
            //获注解所标注的方法所在的类的类名
            String className = target.getClass().getName();
            //获取注解索标注的方法的类名
            String methodName = method.getName();
            return className + '_' + methodName+"_"+ param[0].toString();
        };
    }
}
