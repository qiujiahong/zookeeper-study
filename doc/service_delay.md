# 服务的暴露延迟

服务可以通过设置延迟暴露

```xml
    <dubbo:service interface="com.nick.service.SomeService"
                   ref="otherService" version="2.0.0"
                    delay="5000"/>

```