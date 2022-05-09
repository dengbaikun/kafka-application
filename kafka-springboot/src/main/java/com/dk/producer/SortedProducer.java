package com.itheima.demo.controller;

import com.alibaba.fastjson.JSON;
import com.itheima.demo.config.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SortedProducer {
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/kafka/test/{id}/{status}")
    public void sendSortedMsg(@PathVariable("id") int id ,@PathVariable("status") int status ) {
        Order order = new Order();
        order.setId(id);
        order.setStatus(status);
//        以订单号做key发送
        kafkaTemplate.send("sorted",String.valueOf(id), JSON.toJSONString(order));
    }
}