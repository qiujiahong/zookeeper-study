# !/bin/bash 

# hostnamectl set-hostname m1
yum install -y vim wget unzip zip 

curl -s "https://get.sdkman.io" | bash
source "/root/.sdkman/bin/sdkman-init.sh"
# 如果要安装其他版本java，可以通过命令获取可以安装版本 sdk list java
sdk install java 8.0.201-oracle 
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
cd /opt/apps/zookeeper/conf/
cp zoo_sample.cfg zoo.cfg
# 修改配置文件中的 ``dataDir=/usr/data/zookeeper``
sed -i "s/dataDir.*/dataDir=\/usr\/data\/zookeeper/g" zoo.cfg 
mkdir -p /usr/data/zookeeper

cd /opt/apps/zookeeper/bin/
# ./zkServer.sh start
# 检查服务状态
# ./zkServer.sh status
# ps -ef | grep zookeeper

