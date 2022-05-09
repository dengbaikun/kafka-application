package com.itheima.demo.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//测试同步发送与监听
@RestController
public class AsyncProducer {
    private final static Logger logger = LoggerFactory.getLogger(AsyncProducer.class);
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    //同步发送
    @GetMapping("/kafka/sync/{msg}")
    public void sync(@PathVariable("msg") String msg) throws Exception {
        Message message = new Message();
        message.setMessage(msg);
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test", JSON.toJSONString(message));
        SendResult<String, Object> result = future.get(3,TimeUnit.SECONDS);
        logger.info("send result:{}",result.getProducerRecord().value());
    }

}