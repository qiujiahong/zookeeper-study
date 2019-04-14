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

如上图所示，准备3台centos主机。一台将会作为leader，其余2台被作为follower。笔者三台主机ip如下：

```
server.1=192.168.10.11
server.2=192.168.10.12
server.3=192.168.10.13
```

## 安装步骤(三台主机分别操作)


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
server.4=192.168.10.14:2888:3888:observer

```

* 启动集群

```
cd /opt/apps/zookeeper/bin
./zkServer.sh start 
```
*  查看状态

./zkServer.sh status




## 其他

* 防火墙设置

```bash
# 查看防火墙状态
systemctl status firewalld
# 启动防火墙
systemctl start firewalld
# 添加
firewall-cmd --zone=public --add-port=80/tcp --permanent 
# 重新载入
firewall-cmd --reload
# 查看
firewall-cmd --zone=public --query-port=80/tcp
# 删除
firewall-cmd --zone=public --remove-port=80/tcp --permanent
```

