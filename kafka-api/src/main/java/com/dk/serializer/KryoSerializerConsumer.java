package com.dk.serializer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author DK
 * @version 1.0
 * @date 2020/11/6 16:01
 **/
public class KryoSerializerConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.32.5:9092,192.168.32.6:9092,192.168.32.7:9092");
        props.put("group.id", "gp-ser-group");
        // 是否自动提交偏移量，只有commit之后才更新消费组的 offset
        props.put("enable.auto.commit", "true");
        // 消费者自动提交的间隔
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "com.dk.serializer.KryoSerializer");

        KafkaConsumer<String, Teacher> consumer = new KafkaConsumer<>(props);
        // 订阅队列
        consumer.subscribe(Collections.singletonList("ser-topic"));
        try {
            while (true) {
                ConsumerRecords<String, Teacher> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, Teacher> record : records) {
                    System.out.printf("offset = %d ,key =%s, value= %s, partition= %s%n", record.offset(), record.key(), record.value(), record.partition());
                }
            }
        } finally {
            consumer.close();
        }
    }

}

