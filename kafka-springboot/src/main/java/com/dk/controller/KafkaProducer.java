package com.itheima.demo.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

//普通轮询发送（默认策略）
@RestController
public class KafkaProducer {
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/kafka/test/{msg}")
    public void sendMessage(@PathVariable("msg") String msg) {
        Message message = new Message();
        message.setMessage(msg);
        kafkaTemplate.send("test", JSON.toJSONString(message));
    }
}