package com.dk.assign;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author DK
 * @version 1.0
 * @date 2020/11/7 17:57
 **/
public class ConsumerAutoAssignTest {
    public static void main(String[] args) {
        Properties props= new Properties();
        props.put("bootstrap.servers","192.168.32.5:9092,192.168.32.6:9092,192.168.32.7:9092");
        props.put("group.id","gp-assign-group-1");
        // 是否自动提交偏移量，只有commit之后才更新消费组的 offset
        props.put("enable.auto.commit","true");
        // 消费者自动提交的间隔
        props.put("auto.commit.interval.ms","1000");
        // 从最早的数据开始消费 earliest | latest | none
        props.put("auto.offset.reset","earliest");
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        // 结果 c1 : p0 p1 p2  c2 : p3 p4 （默认）
        // props.put("partition.assignment.strategy","org.apache.kafka.clients.consumer.RangeAssignor");

        // 结果 c1 : p0 p2 p4  c2 : p1 p3
        //props.put("partition.assignment.strategy","org.apache.kafka.clients.consumer.RoundRobinAssignor");

        // 结果 c1 : p2 p3 p4  c2 : p0 p1
        //props.put("partition.assignment.strategy","org.apache.kafka.clients.consumer.StickyAssignor");

        // 两个消费者消费5个分区
        KafkaConsumer<String,String> consumer1=new KafkaConsumer<String, String>(props);
        KafkaConsumer<String,String> consumer2=new KafkaConsumer<String, String>(props);

        // 订阅队列
        consumer1.subscribe(Arrays.asList("ass5part"));
        consumer2.subscribe(Arrays.asList("ass5part"));

        try {
            while (true){
                ConsumerRecords<String,String> msg1=consumer1.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String,String> record:msg1){
                    System.out.printf("----consume1----offset = %d ,key =%s, value= %s, partition= %s%n" ,record.offset(),record.key(),record.value(),record.partition());
                }

                ConsumerRecords<String,String> msg2=consumer2.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String,String> record:msg2){
                    System.out.printf("----consume2----offset = %d ,key =%s, value= %s, partition= %s%n" ,record.offset(),record.key(),record.value(),record.partition());
                }
            }
        }finally {
            consumer1.close();
        }
    }

}
