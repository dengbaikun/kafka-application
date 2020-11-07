package com.dk.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author DK
 * @version 1.0
 * @date 2020/11/6 10:12
 **/
@Component
public class ConsumerListener {
    @KafkaListener(topics = "springboottopic",groupId = "springboottopic-group")
    public void onMessage(String msg){
        System.out.println("----收到消息："+msg+"----");
    }
}
