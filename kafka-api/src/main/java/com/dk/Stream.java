package com.dk;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStreamBuilder;

import java.util.Properties;

public class Stream {

    /**
     * 通过streamAPI实现将数据从test里面读取出来，写入到test2里面去
     * @param args
     */
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"bigger");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"node01:9092");
        properties.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());//key的序列化和反序列化的类
        properties.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());
        //获取核心类 KStreamBuilder
        KStreamBuilder kStreamBuilder = new KStreamBuilder();
        //通过KStreamBuilder调用stream方法表示从哪个topic当中获取数据
        //调用mapValues方法，表示将每一行value都给取出来
        //line表示我们取出来的一行行的数据
        //将转成大写的数据，写入到test2这个topic里面去
        kStreamBuilder.stream("test").mapValues(line -> line.toString().toUpperCase()).to("test2");
        //通过kStreamBuilder可以用于创建KafkaStream  通过kafkaStream来实现流失的编程启动
        KafkaStreams kafkaStreams = new KafkaStreams(kStreamBuilder, properties);
        kafkaStreams.start();  //调用start启动kafka的流 API
    }

}
