package com.dk.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author DK
 * @version 1.0
 * @date 2020/11/6 10:12
 **/
@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public String send(@RequestParam String msg){
        kafkaTemplate.send("springboottopic", msg);
        return "ok";
    }
}
