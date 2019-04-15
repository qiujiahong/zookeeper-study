# springboot中添加dubbo

一个dubbo项目至少由于三个工程构成：
* 包含service接口，实体类，常量累，工具类等的commons工程；（maven工程）
* 包含service实现类,dao类，mapper映射文件的服务提供者serviceDao工程；(springboot工程)
* 包含springMVC的处理类的服务消费者userWeb工程；(springboot工程)


## 服务提供者

* 添加zkClient依赖、dubbo与springboot整合依赖，及commons工程依赖；   
```xml

            <dependency>
                  <groupId>com.nick</groupId>
                  <artifactId>04-common</artifactId>
                  <version>1.0-SNAPSHOT</version>
              </dependency>
              <!--zk客户端依赖-->
              <dependency>
                  <groupId>com.101tec</groupId>
                  <artifactId>zkclient</artifactId>
                  <version>0.11</version>
              </dependency>
              <!--dubbo与springboot整合依赖-->
              <dependency>
                  <groupId>com.alibaba.spring.boot</groupId>
                  <artifactId>dubbo-spring-boot-starter</artifactId>
                  <version>2.0.0</version>
              </dependency>

```
* 在入口类上添加@EnableDubboConfiguration注解，开启Dubbo自动配置   
```java 
@EnableDubboConfiguration
```

* 在主配置文件中添加spring.application.name属性，指定服务提供者应用名称，添加spring.dubbo.registry属性指定注册中心   
```xml
# 注册dubbo相关配置
spring.application.name=04-servicedata-provider
# 可以是 域名
spring.dubbo.registry=zookeeper://192.168.10.11:2181  

```
* 在service实现添加dubbo的@Service注解，以及@Component注解

```java

@Service   //com.alibaba.dubbo.config.annotation.Service;
@Component

```


