# 安装zookeeper集群


## 架构

```

[node1] ----[node2]
  |          /
  |         /
  |        /  
  |       /
  |      /
[node3]
```

如上图所示，准备3台centos主机。一台将会作为leader，其余2台被作为follower。

## 安装步骤

* 删除data目录下文件，添加文件/usr/data/zookeeper/myid，分别写入数字1,2,3

```
rm -rf /usr/data/zookeeper/* 
echo 1 > /usr/data/zookeeper/myid
```

* 修改配置文件zoo.conf，添加如下配置 

```
# server.[myid]=[ip]:[port1]:[port2]
# myid  - data目录下文件，添加文件myid文件里面的值
# ip    - 可以是ip或者域名，或者主机名
# port1 - 链接端口号
# port2 - 选举端口号
server.1=192.168.10.11:2888:3888
server.2=192.168.10.12:2888:3888
server.3=192.168.10.13:2888:3888
```


## 启动集群

cd /opt/apps/zookeeper/bin
./zkServer.sh start 

*  查看状态

./zkServer.sh status










