package com.itheima.demo.config;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

public class MyDeserializer implements Deserializer {
    private final static Logger logger = LoggerFactory.getLogger(MyDeserializer.class);

    @Override
    public Object deserialize(String s, byte[] bytes) {
        try {
            String json = new String(bytes,"utf-8");
            return JSON.parse(json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
