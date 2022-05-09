package com.itheima.demo.config;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class MySerializer implements Serializer {

    @Override
    public byte[] serialize(String s, Object o) {
        String json = JSON.toJSONString(o);
        return json.getBytes();
    }

}
