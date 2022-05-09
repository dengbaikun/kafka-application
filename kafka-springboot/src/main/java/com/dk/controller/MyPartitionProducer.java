package com.dk.controller;

import com.itheima.demo.config.MyPartitionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//测试自定义分区发送
@RestController
public class MyPartitionProducer {

    @Autowired
    MyPartitionTemplate template;

//    使用0开头和其他任意字母开头的key发送消息
//    看控制台的输出，在哪个分区里？
    @GetMapping("/kafka/myPartitionSend/{key}")
    public void setPartition(@PathVariable("key") String key) {
        template.getKafkaTemplate().send("test", key,"key="+key+"，msg=自定义分区策略");
    }


}