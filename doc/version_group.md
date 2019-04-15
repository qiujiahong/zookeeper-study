# 多版本控制和分组

## 前言

dubbo支持多版本控制和group：
* 多版本控制，同一个服务写成多个版本，方便升级；
* 分组也是针对相同接口给出多种实现类，但是不同的是，这些不同实现类没有谁替换掉谁的意思，是针对不同需求，或者不同功能模块给出的不同实现。



## 多版本


*  provider 

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

    <!--当前工程名称，将在监控平台使用-->
    <dubbo:application name="03-provider-version" />

    <!--注册service,其就是真正服务器提供者-->
    <bean id="someService" class="com.nick.service.SomeServiceImpl"></bean>
    <bean id="otherService" class="com.nick.service.OtherServiceImpl"></bean>

    <!--声明zk服务器中心-->
    <!--单体-->
    <dubbo:registry address="zookeeper://192.168.10.11:2181?backup=192.168.10.12:2181,192.168.10.13:2181,192.168.10.14:2181" />
    <!--集群-->
    <!--<dubbo:registry address="zookeeper://192.168.10.11:2181" />-->
    <!--<dubbo:registry protocol="zookeeper" address="192.168.11:2181" />-->

    <!--暴露服务，将服务暴露给zk服务中心-->
    <dubbo:service interface="com.nick.service.SomeService"
                   ref="someService" version="1.0.0"/>

    <dubbo:service interface="com.nick.service.SomeService"
                   ref="otherService" version="2.0.0"/>


</beans>
```


* consumer 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://dubbo.apache.org/schema/dubbo http://dubbo.apache.org/schema/dubbo/dubbo.xsd">
    <!--当前工程的名称，监控中心的使用-->
    <dubbo:application name="03-consumer-version"/>
    
    <!--集群-->
    <dubbo:registry address="zookeeper://192.168.10.11:2181?backup=192.168.10.12:2181,192.168.10.13:2181,192.168.10.14:2181" />

    <dubbo:reference  id="service"
                      interface="com.nick.service.SomeService" version="2.0.0 ">
        <dubbo:method name="hello" cache="lru"  />
    </dubbo:reference>

</beans>
```


## 分组


* provider
```xml

    <bean id="some" class="com.nick.service.SomeServiceImpl"></bean>
    <bean id="other" class="com.nick.service.OtherServiceImpl"></bean>
    <dubbo:reference  id="some" cache="true"
                      interface="com.nick.service.ISomeService" group="pay.weixin"/>
    <dubbo:reference  id="other" cache="true"
                      interface="com.nick.service.IOtherService" group="pay.alipay"/>

```


* consumer 

```xml
 <dubbo:reference  id="some" cache="true"
                      interface="com.nick.service.ISomeService" group="pay.alipay"/>
```