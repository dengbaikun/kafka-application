package com.dk;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MyProducer {
    /**
     * 实现生产数据到kafka test这个topic里面去
     * @param args
     */
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "node01:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //获取kafakProducer这个类
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);
        //使用循环发送消息
        for(int i =0;i<100;i++){
           // kafkaProducer.send(new ProducerRecord<String, String>("test","mymessage" + i));
            kafkaProducer.send(new ProducerRecord<String, String>("mypartition","mymessage" + i));
        }
        //关闭生产者
        kafkaProducer.close();
    }

}
