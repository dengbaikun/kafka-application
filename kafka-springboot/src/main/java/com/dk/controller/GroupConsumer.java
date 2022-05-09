package com.itheima.demo.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

//测试组消费
//@Component
public class GroupConsumer {
    private final Logger logger = LoggerFactory.getLogger(GroupConsumer.class);

    //组1，消费者1
    @KafkaListener(topics = {"test"},groupId = "group1")
    public void onMessage1(ConsumerRecord<?, ?> consumerRecord) {
        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
        if (optional.isPresent()) {
            Object msg = optional.get();
            logger.info("group:group1-1 , message:{}", msg);
        }
    }

    //组1，消费者2
    @KafkaListener(topics = {"test"},groupId = "group1")
    public void onMessage2(ConsumerRecord<?, ?> consumerRecord) {
        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
        if (optional.isPresent()) {
            Object msg = optional.get();
            logger.info("group:group1-2 , message:{}", msg);
        }
    }

    //组2，只有一个消费者
    @KafkaListener(topics = {"test"},groupId = "group2")
    public void onMessage3(ConsumerRecord<?, ?> consumerRecord) {
        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
        if (optional.isPresent()) {
            Object msg = optional.get();
            logger.info("group:group2 , message:{}", msg);
        }
    }
}