package com.dk.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//测试分区发送
@RestController
public class PartitionProducer {
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

//    指定分区发送
//    不管你key是什么，到同一个分区
    @GetMapping("/kafka/partitionSend/{key}")
    public void setPartition(@PathVariable("key") String key) {
        kafkaTemplate.send("test", 0,key,"key="+key+"，msg=指定0号分区");
    }

//    指定key发送，不指定分区
//    根据key做hash，相同的key到同一个分区
    @GetMapping("/kafka/keysend/{key}")
    public void setKey(@PathVariable("key") String key) {
        kafkaTemplate.send("test", key,"key="+key+"，msg=不指定分区");
    }


}