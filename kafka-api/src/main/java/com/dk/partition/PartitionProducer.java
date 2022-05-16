package com.dk.partition;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class PartitionProducer {

    /**
     * kafka生产数据 通过不同的方式，将数据写入到不同的分区里面去
     *
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

        //配置我们自定义分区类
        props.put("partitioner.class","cn.itcast.kafka.partition.MyPartitioner");


        //获取kafakProducer这个类
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);
        //使用循环发送消息
        for(int i =0;i<100;i++){
            //分区策略第一种，如果既没有指定分区号，也没有指定数据key，那么就会使用轮询的方式将数据均匀的发送到不同的分区里面去
            //ProducerRecord<String, String> producerRecord1 = new ProducerRecord<>("mypartition", "mymessage" + i);
            //kafkaProducer.send(producerRecord1);
            //第二种分区策略 如果没有指定分区号，指定了数据key，通过key.hashCode  % numPartitions来计算数据究竟会保存在哪一个分区里面
            //注意：如果数据key，没有变化   key.hashCode % numPartitions  =  固定值  所有的数据都会写入到某一个分区里面去
            //ProducerRecord<String, String> producerRecord2 = new ProducerRecord<>("mypartition", "mykey", "mymessage" + i);
            //kafkaProducer.send(producerRecord2);
            //第三种愤分区策略：如果指定了分区号，那么就会将数据直接写入到对应的分区里面去
          //  ProducerRecord<String, String> producerRecord3 = new ProducerRecord<>("mypartition", 0, "mykey", "mymessage" + i);
           // kafkaProducer.send(producerRecord3);
            //第四种分区策略：自定义分区策略。如果不自定义分区规则，那么会将数据使用轮询的方式均匀的发送到各个分区里面去
            kafkaProducer.send(new ProducerRecord<String, String>("mypartition","mymessage"+i));
        }
        //关闭生产者
        kafkaProducer.close();
    }

}
