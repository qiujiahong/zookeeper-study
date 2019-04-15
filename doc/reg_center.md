# 多注册中心

同一个服务可以注册到不同地域的多个注册中心，以便为不同地域的服务消费者提供更更为快捷的服务。   
修改服务提供者配置文件，多个注册中心之间使用逗号分割开。 



## 同一个服务注册到不同的注册中心
 
 
```xml
<!-- 声明注册中心 -->
    <dubbo:registry id="bjCenter" address="zookeeper://192.168.10.11:2181" />
    <dubbo:registry id="shCenter" address="zookeeper://192.168.10.11:2181" />


    <dubbo:service interface="com.nick.service.SomeService"
                   ref="otherService"
                   register="bjCenter,shCenter"/>

```


## 不同服务注册到不同注册中心


 
```xml
<!-- 声明注册中心 -->
    <dubbo:registry id="bjCenter" address="zookeeper://192.168.10.11:2181" />
    <dubbo:registry id="shCenter" address="zookeeper://192.168.10.11:2181" />


    <dubbo:service interface="com.nick.service.ISomeService"
                   ref="otherService"
                   register="bjCenter"/>
    <dubbo:service interface="com.nick.service.IOtherService"
                   ref="otherService"
                   register="shCenter"/>
                   

```


