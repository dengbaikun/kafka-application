package com.dk.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Optional;

//指定消费组消费
//@Component
public class PartitionConsumer {
    private final Logger logger = LoggerFactory.getLogger(PartitionConsumer.class);

    //分区消费
    @KafkaListener(topics = {"test"},topicPattern = "0")
    public void onMessage(ConsumerRecord<?, ?> consumerRecord) {
        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
        if (optional.isPresent()) {
            Object msg = optional.get();
            logger.info("partition=0,message:[{}]", msg);
        }
    }
    @KafkaListener(topics = {"test"},topicPattern = "1")
    public void onMessage1(ConsumerRecord<?, ?> consumerRecord) {
        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
        if (optional.isPresent()) {
            Object msg = optional.get();
            logger.info("partition=1,message:[{}]", msg);
        }
    }
}