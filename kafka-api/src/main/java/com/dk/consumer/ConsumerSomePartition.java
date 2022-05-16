package com.dk.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerSomePartition {
    //实现消费一个topic里面某些分区里面的数据
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
        props.put("group.id", "test_group");  //消费组
        props.put("enable.auto.commit", "true");//允许自动提交offset
        props.put("auto.commit.interval.ms", "1000");//每隔多久自动提交offset
        props.put("session.timeout.ms", "30000");
        //指定key，value的反序列化类
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        //获取kafkaConsumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //通过consumer订阅某一个topic，进行消费.会消费topic里面所有分区的数据
       // consumer.subscribe();

        //通过调用assign方法实现消费mypartition这个topic里面的0号和1号分区里面的数据

        TopicPartition topicPartition0 = new TopicPartition("mypartition", 0);
        TopicPartition topicPartition1 = new TopicPartition("mypartition", 1);
        //订阅我们某个topic里面指定分区的数据进行消费
        consumer.assign(Arrays.asList(topicPartition0,topicPartition1));

        int i =0;
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                i++;
                System.out.println("数据值为"+ record.value()+"数据的offset为"+ record.offset());
                System.out.println("消费第"+i+"条数据");
            }

        }






    }

}
