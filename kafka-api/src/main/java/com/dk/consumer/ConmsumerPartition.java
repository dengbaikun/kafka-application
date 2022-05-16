package com.dk.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

public class ConmsumerPartition {

    /**
     * 处理完每一个分区里面的数据，就马上提交这个分区里面的数据
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
        kafkaConsumer.subscribe(Arrays.asList("mypartition"));
        while (true){
            //通过while ture进行消费数据
            ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
            //获取mypartition这个topic里面所有的分区
            Set<TopicPartition> partitions = records.partitions();
            //循环遍历每一个分区里面的数据，然后将每一个分区里面的数据进行处理，处理完了之后再提交每一个分区里面的offset
            for (TopicPartition partition : partitions) {
                //获取每一个分区里面的数据
                List<ConsumerRecord<String, String>> records1 = records.records(partition);
                for (ConsumerRecord<String, String> record : records1) {
                    System.out.println(record.value()+"===="+ record.offset());
                }
                //获取我们分区里面最后一条数据的offset，表示我们已经消费到了这个offset了
                long offset = records1.get(records1.size() - 1).offset();
                //提交offset
                //提交我们的offset，并且给offset加1  表示我们下次从没有消费的那一条数据开始消费
                kafkaConsumer.commitSync(Collections.singletonMap(partition,new OffsetAndMetadata(offset + 1)));
            }
        }
    }
}
