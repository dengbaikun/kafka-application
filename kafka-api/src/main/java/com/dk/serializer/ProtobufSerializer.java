package com.dk.serializer;

import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @author DK
 * @version 1.0
 * @date 2020/11/6 16:01
 **/
public class ProtobufSerializer implements Serializer<Protobufable> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public byte[] serialize(String topic, Protobufable data) {
        return data.encode();
    }

    @Override
    public void close() {}
}


