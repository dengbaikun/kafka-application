package com.dk.transaction;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
/**
 * @author DK
 * @version 1.0
 * @date 2020/11/5 11:49 下午
 */
public class TransactionProducer {
    public static void main(String[] args) {
        Properties props=new Properties();
        props.put("bootstrap.servers","192.168.32.5:9092,192.168.32.6:9092,192.168.32.7:9092");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        // 0 发出去就确认 | 1 leader 落盘就确认| all或-1 所有Follower同步完才确认
        props.put("acks","all");
        // 异常自动重试次数
        props.put("retries",3);
        // 多少条数据发送一次，默认16K
        props.put("batch.size",16384);
        // 批量发送的等待时间
        props.put("linger.ms",5);
        // 客户端缓冲区大小，默认32M，满了也会触发消息发送
        props.put("buffer.memory",33554432);
        // 获取元数据时生产者的阻塞时间，超时后抛出异常
        props.put("max.block.ms",3000);

        props.put("enable.idempotence",true);
        // 事务ID，唯一
        props.put("transactional.id", UUID.randomUUID().toString());

        Producer<String,String> producer = new KafkaProducer<String,String>(props);

        // 初始化事务
        producer.initTransactions();

        try {
            producer.beginTransaction();
            producer.send(new ProducerRecord<String,String>("transaction-test","1","1"));
            producer.send(new ProducerRecord<String,String>("transaction-test","2","2"));
            // Integer i = 1/0;
            producer.send(new ProducerRecord<String,String>("transaction-test","3","3"));
            // 提交事务
            producer.commitTransaction();
        } catch (KafkaException e) {
            // 中止事务
            producer.abortTransaction();
        }


        producer.close();
    }
}
