# 方法缓存结果


## 简介

dubbo 支持声明式缓存，能够将查询结果缓存到本地。


## 使用方法

如下``cache="true"``, 打开方法结果缓存，如果同样参数多次调用，则会使用上次缓存。 

```xml
    <dubbo:reference  id="someService" cache="true"
                      interface="com.nick.service.SomeService"/>
```

## 支持的三种缓存机制

* lru：服务级别缓存的默认机制，该机制可以缓存1000个结果，若超出1000，则采用最近最少使用原则；
* threadlocal: 当前线程缓存，当前线程要对当前某一操作时首先需要查询当前线程的某个信息，通过线程缓存，则可减少查询；
* jcache： 可桥接各种缓存，实现第三方缓存产品；


> 智能用在查询结果不变的场景中。

