package com.dk;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MannualConsumer {
    /**
     * 实现手动的提交offset
     * @param args
     */
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "node01:9092");
        props.put("group.id", "test_group");
        props.put("enable.auto.commit", "false"); //禁用自动提交offset，后期我们手动提交offset
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Arrays.asList("test"));  //订阅test这个topic
        int minBatchSize = 200;  //达到200条进行批次的处理，处理完了之后，提交offset
        List<ConsumerRecord<String, String>> consumerRecordList = new ArrayList<>();//定义一个集合，用于存储我们的ConsumerRecorder
        while (true){

            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(1000);
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                consumerRecordList.add(consumerRecord);
            }
            if(consumerRecordList.size() >=  minBatchSize){
                //如果集合当中的数据大于等于200条的时候，我们批量进行处理
                //将这一批次的数据保存到数据库里面去
                //insertToDb(consumerRecordList);
                System.out.println("手动提交offset的值");
                //提交offset，表示这一批次的数据全部都处理完了
               // kafkaConsumer.commitAsync();  //异步提交offset值
                kafkaConsumer.commitSync();//同步提交offset的值
                consumerRecordList.clear();//清空集合当中的数据
            }
        }
    }
}
