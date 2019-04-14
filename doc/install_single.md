# 安装单节点zookeeper

## 前言

本文在centos 7中安装zookeeper。测试环境我们使用vagrant来创建一个centos虚拟机。


如果读者对vagrant 不熟悉请百度自行补脑。


## 环境准备

* 新建文件夹zookeeper,在该文件夹下新建文件``Vagrantfile``（如下文件）,启动系统


```ruby
Vagrant.configure("2") do |config|
  config.vm.provision "shell", inline: "echo Hello"
  config.vm.provision "shell", inline: "su"
  config.vm.provision "shell", inline: "sudo echo '123456' | passwd --stdin root "

  config.vm.define "m1" do |m1|
    m1.vm.box = "centos/7"
    m1.vm.box_version = "1811.02"
    m1.vm.network "private_network", ip: "192.168.10.11"
    m1.vm.provider "virtualbox" do |v|
      v.memory = 2048
      v.cpus = 2
    end
  end
end

```


* 登陆系统，安装必要软件，修改主机名

```bash
[root@localhost vagrant]# hostnamectl set-hostname m1
[root@localhost vagrant]# yum install -y vim wget unzip zip 
```

## 安装

* 安装java

```bash
curl -s "https://get.sdkman.io" | bash
source "/root/.sdkman/bin/sdkman-init.sh"
# 如果要安装其他版本java，可以通过命令获取可以安装版本 sdk list java
sdk install java 8.0.201-oracle 
```

* 下载,zookeeper,更多下载地址见[官网](https://www.apache.org/dyn/closer.cgi/zookeeper/)  

```
wget http://mirror.bit.edu.cn/apache/zookeeper/zookeeper-3.4.14/zookeeper-3.4.14.tar.gz
mkdir /opt/apps
tar -xzvf zookeeper-3.4.14.tar.gz -C /opt/apps/
cd /opt/apps
# 创建一个链接解除掉版本依赖
ln -s zookeeper-3.4.14/ ./zookeeper
# zk的bin注册到环境变量中去
echo "export ZK_HOME=/opt/apps/zookeeper" >>/etc/profile
echo "PATH=$ZK_HOME/bin:$PATH" >>/etc/profile
source /etc/profile
```


* 修改配置文件

```
cd zookeeper/conf/
cp zoo_sample.cfg zoo.cfg
# 修改配置文件中的 ``dataDir=/usr/data/zookeeper``
sed -i "s/dataDir.*/dataDir=\/usr\/data\/zookeeper/g" zoo.cfg 
mkdir -p /usr/data/zookeeper
```


* 启动
```
cd /opt/apps/zookeeper/bin/
./zkServer.sh start
# 检查服务状态
./zkServer.sh status
ps -ef | grep zookeeper
```
