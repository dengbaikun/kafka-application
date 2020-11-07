package com.dk.partition;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * @author DK
 * @version 1.0
 * @date 2020/11/7 17:57
 **/
public class ProducerAutoPartition {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props=new Properties();
        props.put("bootstrap.servers","192.168.32.5:9092,192.168.32.6:9092,192.168.32.7:9092");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("acks","-1");
        props.put("retries",3);
        props.put("partitioner.class", "com.qingshan.partition.SimplePartitioner");
        props.put("batch.size",16384);
        props.put("linger.ms",10);
        props.put("buffer.memory",33554432);
        props.put("max.block.ms",5000);

        KafkaProducer<String, Integer> producer = new KafkaProducer<String,Integer>(props);

        String topic = "qs4part2673";
        int partitionSize = producer.partitionsFor(topic).size();
        System.out.println("Partition size: " + partitionSize);

        for (int i = 0; i < 10; i++) {
            // 使用自定义分区器选择分区
            // 默认是对key进行hash取余
            ProducerRecord<String, Integer> producerRecord = new ProducerRecord<String,Integer>(topic, i+"", i);
            RecordMetadata metadata = producer.send(producerRecord).get();
            System.out.println("Sent to partition: " + metadata.partition() + ", offset: " + metadata.offset());
        }
    }

}
