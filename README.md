# [kafka使用](http://kafka.apache.org/)

## 单机搭建

启动zookeeper
kafka需要依赖ZK，安装包中已经自带了一个ZK，也可以改成指定已运行的ZK。
如果改成指定的ZK需要修改修改 kafka 安装目录下的 config/server.properties 文件中的 zookeeper.connect 。这里使用自带的ZK。

后台启动ZK：

nohup ./bin/zookeeper-server-start.sh config/zookeeper.properties >> zookeeper.nohup &
检查zookeeper是否启动成功：

ps -ef|grep zookeeper
启动kafka
修改相关配置

vim config/server.properties
Broker ID启动以后就不能改了

broker.id=1
取消注释，改成本机IP：

listeners=PLAINTEXT://192.168.44.160:9092
num.partitions后面增加2行。
发送到不存在topic自动创建。允许永久删除topic。

num.partitions=1
auto.create.topics.enable=true
delete.topic.enable=true
后台启动kafka（kafka安装目录下）：

nohup ./bin/kafka-server-start.sh ./config/server.properties & 
日志在logs目录下

创建Topic
创建一个名为gptest的topic，只有一个副本，一个分区：

sh bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic gptest
查看已经创建的 topic：

sh bin/kafka-topics.sh -list -zookeeper localhost:2181
启动Producer
打开一个窗口，在kafka解压目录下：

sh bin/kafka-console-producer.sh --broker-list localhost:9092 --topic gptest
启动Consumer
在一个新的远程窗口中：

sh bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic gptest --from-beginning
Producer窗口发送消息
输入hello world 回车
20200709_144635.png

消费者收到了消息：
20200709_144648.png

删除kafka全部数据步骤：
1、停止每台机器上的kafka；
2、删除kafka存储目录（server.properties文件log.dirs配置，默认为“/tmp/kafka-logs”）全部topic的数据目录；
3、删除zookeeper上与kafka相关的znode节点；除了/zookeeper
4、重启kafka。

## 基本命令使用

一、脚本概览
bin目录下的脚本作用

脚本	作用
```text 
connect-distributed.sh	用于启动多节点的Distributed模式的Kafka Connect组件
connect-standalone.sh	用于启动单节点的Standalone模式的Kafka Connect组件
kafka-acls.sh	用于设置Kafka权限，比如设置哪些用户可以访问Kafka的哪些TOPIC的权限
kafka-broker-api-versions.sh	主要用于验证不同Kafka版本之间服务器和客户端的适配性
kafka-configs.sh	配置管理脚本
kafka-console-consumer.sh	kafka消费者控制台
kafka-console-producer.sh	kafka生产者控制台
kafka-consumer-groups.sh	kafka消费者组相关信息
kafka-consumer-perf-test.sh	kafka消费者性能测试脚本
kafka-delegation-tokens.sh	用于管理Delegation Token。基于Delegation Token的认证是一种轻量级的认证机制，是对SASL认证机制的补充。
kafka-delete-records.sh	用于删除Kafka的分区消息
kafka-dump-log.sh	用于查看Kafka消息文件的内容
kafka-log-dirs.sh	用于查询各个Broker上的各个日志路径的磁盘占用情况
kafka-mirror-maker.sh	用于在Kafka集群间实现数据镜像
kafka-preferred-replica-election.sh	用于执行Preferred Leader选举，可以为指定的主题执行更换Leader的操作
kafka-producer-perf-test.sh	kafka生产者性能测试脚本
kafka-reassign-partitions.sh	用于执行分区副本迁移以及副本文件路径迁移。
kafka-replica-verification.sh	复制进度验证脚本
kafka-run-class.sh	用于执行任何带main方法的Kafka类
kafka-server-start.sh	启动kafka服务
kafka-server-stop.sh	停止kafka服务
kafka-streams-application-reset.sh	用于给Kafka Streams应用程序重设位移，以便重新消费数据
kafka-topics.sh	topic管理脚本
kafka-verifiable-consumer.sh	可检验的kafka消费者
kafka-verifiable-producer.sh	可检验的kafka生产者
trogdor.sh	Kafka的测试框架，用于执行各种基准测试和负载测试
zookeeper-server-start.sh	启动zk服务
zookeeper-server-stop.sh	停止zk服务
zookeeper-shell.sh	zk客户端
```
二、Broker服务
启动服务

./kafka-server-start.sh ../config/server.properties
后台启动

./kafka-server-start.sh -daemon ../config/server.properties
停止服务

./kafka-server-stop.sh ../config/server.properties
三、元数据
创建topic

./kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic my-test-topic
查看所有topic

./kafka-topics.sh --bootstrap-server localhost:9092 --list
从Kafka 2.2版本开始，Kafka社区推荐用–bootstrap-server参数替换–zookeeper参数用于指定Kafka Broker。集群的多个IP端口用逗号,隔开

查看topic详细信息

kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic topic_name
给topic增加分区（只能增加不能减少）

./kafka-topics.sh --bootstrap-server localhost:9092 --alter --topic my-test-topic --partitions 10
删除topic（标记删除）

./kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic my-test-topic
永久删除需要修改配置文件：delete.topic.enable=true

强制删除TOPIC的方法：
A、手动删除ZooKeeper节点/admin/delete_topics下以待删除TOPIC为名的znode。
B、手动删除TOPIC在磁盘上的分区目录。
C、在ZooKeeper中执行rmr /controller，触发Controller重选举，刷新Controller缓存。可能会造成大面积的分区Leader重选举。可以不执行，只是Controller缓存中没有清空待删除TOPIC，不影响使用。

修改partition副本数：
先配置一个reassign.json文件，内容：
例如my-test-topic有3个分区，原来只有一个副本，增加到2个副本

{"version":1, "partitions":[
 {"topic":"my-test-topic","partition":0,"replicas":[0,1]}, 
  {"topic":"my-test-topic","partition":1,"replicas":[1,2]},
  {"topic":"my-test-topic","partition":2,"replicas":[2,0]}
]}
执行kafka-reassign-partitions脚本：

./kafka-reassign-partitions.sh --zookeeper localhost:2181 --reassignment-json-file reassign.json --execute
四、生产者
发送消息

./kafka-console-producer.sh --broker-list localhost:9092 --topic my-test-topic
五、消费者
消费消息
```shell script
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my-test-topic 
```

查看消费者组提交的位移数据:
```shell script
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic __consumer_offsets --formatter "kafka.coordinator.group.GroupMetadataManager\$OffsetsMessageFormatter" --from-beginning
```

## 基于Canal和Kafka实现数据同步

在目标数据库上创建用户和数据库

```text
数据库首先要开启binlog，binlog-format必须是ROW
log-bin=/var/lib/mysql/mysql-bin
binlog-format=ROW
-- 创建canal专用的用户，用于访问master获取binlog

GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;

-- 给canal用户分配查询和复制的权限
GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO canal@'%';

-- 刷新权限
FLUSH PRIVILEGES;

ALTER USER 'canal'@'%' IDENTIFIED WITH mysql_native_password BY '123456';

-- 创建测试数据库
CREATE DATABASE `canaltest` CHARSET `utf8mb4` COLLATE `utf8mb4_unicode_ci`;
```
 下载安装canal
 ```text
cd /usr/local/soft/
mkdir canal
cd canal
wget https://github.com/alibaba/canal/releases/download/canal-1.1.4/canal.deployer-1.1.4.tar.gz
tar -zxvf canal.deployer-1.1.4.tar.gz
 ```

需要修改的配置项：
conf/canal.properties
```text
canal.serverMode=kafka
canal.mq.servers = 192.168.44.160:9092
```
example/instance.properties
```text
canal.instance.master.address=192.168.44.121:3306
canal.instance.dbUsername=canal
canal.instance.dbPassword=123456
# 新增
canal.instance.defaultDatabaseName=canaltest
# 这个topic会自动创建
canal.mq.topic=canal-topic
```

在bin目录下启动canal
```text
sh startup.sh 
# 查看实例日志
tail -100f /usr/local/soft/canal/logs/canal/canal.log
```

建表测试
```text
在canaltest数据库随便建一张表，做增删改的操作。

在kafka服务器上消费这个topic
./kafka-console-consumer.sh --bootstrap-server 192.168.44.160:9092 --topic canal-topic
```

## 消息幂等性

1、PID(Producter ID)

2、sequ number

## 事务

### 什么时候需要事务

1、发送多条消息

2、发送消息到多个topic或者多个partition

3、消费以后发出消息 consumer-process-produce

### 事务的实现原理

1、2PC

2、Transaction Coordinator

3、事务日志：topic_transaction_state

4、生产者事务ID:transaction.id

### 事务操作流程

A:生产者通过initTransaction API向Coordinator注册事务ID

B:Coordinator记录事务日志

C:生产者把消息写入目标分区

D:分区和Coordinator的交互，当事务完成以后，消息的状态应该是已提交，这样消费者才可以消费到消息

## kafka特性

* 高吞吐、低延迟
* 高伸缩
* 持久性、可靠性
* 容错性
* 高并发

# kafka原理

## acks

1、ack=0 不需要确认

2、ack=1 leader 确定

3、ack=-1/all leader及其follower确认

## 索引

1、采用稀疏索引

2、O(log2n)+O(m) n索引文件个数，m稀疏程度

.index偏移量(offset)索引文件

.timeindex时间戳(timestamp)索引文件

log.message.timestamp.type=CreateTime/LogAppendTime

### 索引检索过程

1、根据offset判断在哪个segment中

2、在segment的indexfile中，根据offset找到消息的partition

2、根据partition从log文件中比较，最终找到消息

### segment创建由log时间或大小以及索引文件大小

1、log.segment.bytes

2、log.roll.hours

3、log.roll.ms

4、log.index.size.max.bytes

## 清理日志策略

开关：log.cleaner.enable=true

策略：log.cleanup.policy=delete/compact

周期：log.retention.check.intervalms=300000

过期定义：

log.retention.hours

log.retention.minutes

log.retention.ms

文件限制

log.retention.bytes

log.segment.bytes   

## 高可用架构之leader选举

1、谁来主持选举？Broker Controller ZK/controller

2、谁可以参加选举？AR = ISR{}+OSR 不采用Zab，Raft，采用贴近PacificA

3、主从如何同步？

LEO(Log End Offset)：下一跳等待写入的消息的offset（最新的offset+1）

HW(High Watermark)：ISR中最小的LEO

* Follower节点会向Leader发送一个fetch请求，leader向follower发送数据后，需要更新follower的LEO
* follower接收到数据响应后，依次写入消息并且更新LEO
* Leader更新HW(ISR最小的LEO)

## _consumer_offset存储结构

GroupMetadata：保存了消费者组中各个消费者的信息（每个消费者都有编号）

OffsetAndMetadata：保存了消费者组和各个partition的offset位移信息元数据

## kafka为什么这么快

* 顺序读写
* 索引
* 批量读写和文件压缩
* 零拷贝 （sendfile()）

# cmak

kafka启动加 

```shell
JMX_PORT=9999 bin/kafka-server-start.sh -daemon config/server.properties 
```

修改kafka-run-class.sh,当前hostname则是可以访问，增加“-Djava.rmi.server.hostname=192.168.2.128”

```shell
if [ -z "$KAFKA_JMX_OPTS" ]; then
KAFKA_JMX_OPTS="-Djava.rmi.server.hostname={hostname} -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false "
fi
```

