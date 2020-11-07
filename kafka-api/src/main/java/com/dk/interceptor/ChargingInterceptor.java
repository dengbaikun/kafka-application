package com.dk.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
/**
 * @author DK
 * @version 1.0
 * @date 2020/11/7 17:59
 **/
public class ChargingInterceptor implements ProducerInterceptor<String, String> {
    // 发送消息的时候触发
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        System.out.println("1分钱1条消息，不管那么多反正先扣钱");
        return record;
    }

    // 收到服务端的ACK的时候触发
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        System.out.println("消息被服务端接收啦");
    }

    @Override
    public void close() {
        System.out.println("生产者关闭了");
    }

    // 用键值对配置的时候触发
    @Override
    public void configure(Map<String, ?> configs) {
        System.out.println("configure...");
    }
}
