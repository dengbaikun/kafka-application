package com.dk.serializer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author DK
 * @version 1.0
 * @date 2020/11/6 16:04
 **/
public class KryoSerializerProducer {
    public static void main(String[] args) {
        Properties props=new Properties();
        props.put("bootstrap.servers","192.168.32.5:9092,192.168.32.6:9092,192.168.32.7:9092");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","com.dk.serializer.KryoSerializer");
        // 0 发出去就确认 | 1 leader 落盘就确认| all 所有Follower同步完才确认
        props.put("acks","1");
        // 异常自动重试次数
        props.put("retries",3);
        // 多少条数据发送一次，默认16K
        props.put("batch.size",10);
        // 批量发送的等待时间
        props.put("linger.ms",5);
        // 客户端缓冲区大小，默认32M，满了也会触发消息发送
        props.put("buffer.memory",33554432);
        // 获取元数据时生产者的阻塞时间，超时后抛出异常
        props.put("max.block.ms",3000);

        Producer<String, Teacher> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 1000; i++) {
            Teacher teacher = new Teacher("dk"+i,24,"13288993228");
            producer.send(new ProducerRecord<>("ser-topic", ""+i, teacher));
        }



        producer.close();
    }

}

