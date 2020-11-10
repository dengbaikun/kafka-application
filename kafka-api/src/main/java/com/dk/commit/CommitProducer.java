package com.dk.commit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * @author DK
 * @version 1.0
 * @date 2020/11/7 17:57
 **/
public class CommitProducer {
    public static void main(String[] args) throws InterruptedException {
        Properties props=new Properties();
        props.put("bootstrap.servers","192.168.32.5:9092,192.168.32.6:9092,192.168.32.7:9092");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        // 0 发出去就确认 | 1 leader 落盘就确认| all 所有Follower同步完才确认
        props.put("acks","1");
        // 异常自动重试次数
        props.put("retries",3);
        // 多少条数据发送一次，默认16K
        props.put("batch.size",16384);
        // 批量发送的等待时间
        props.put("linger.ms",5);
        // 客户端缓冲区大小，默认32M，满了也会触发消息发送
        props.put("buffer.memory",33554432);
        // 获取元数据时生产者的阻塞时间，超时后抛出异常
        props.put("max.block.ms",3000);
        CountDownLatch countDownLatch = new CountDownLatch(4);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("server-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(4, 4,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        String json = "{\"userid\":\"000001\",\"agreement\":\"02\",\"deviceType\":\"01\",\"deviceid\":\"1234567890\",\"satelliteDate\":\"2019-05-17 19:14:50\",\"status\":\"0100000000000011\",\"warn\":\"0000000000000000\",\"gpsSignal\":\"10\",\"gsmSignal\":\"20\",\"mileage\":\"51583\",\"drive\":\"-303\",\"model\":\"1\",\"serverDate\":\"2020-06-16 11:26:23\",\"host\":\"127.0.0.1\",\"port\":\"53584\",\"isPosition\":\"1\",\"latitude\":\"32.790134\",\"longitude\":\"117.162896\",\"speed\":\"0\",\"direction\":\"114\",\"oilLevel\":\"917\",\"oilPercentage\":\"0.00\",\"refuelingMark\":\"0\"}";
        executorService.execute(()->{
            try (Producer<String, String> producer = new KafkaProducer<String, String>(props)) {
                for (int i = 0; i < 250000; i++) {
                    producer.send(new ProducerRecord<String, String>("device", Integer.toString(i), json));
                }
            } finally {
                countDownLatch.countDown();
            }
        });
        executorService.execute(()->{
            try (Producer<String, String> producer = new KafkaProducer<String, String>(props)) {
                for (int i = 250000; i < 500000; i++) {
                    producer.send(new ProducerRecord<String, String>("device", Integer.toString(i), json));
                }
            } finally {
                countDownLatch.countDown();
            }
        });
        executorService.execute(()->{
            try (Producer<String, String> producer = new KafkaProducer<String, String>(props)) {
                for (int i = 500000; i < 750000; i++) {
                    producer.send(new ProducerRecord<String, String>("device", Integer.toString(i), json));
                }
            } finally {
                countDownLatch.countDown();
            }
        });
        executorService.execute(()->{
            try (Producer<String, String> producer = new KafkaProducer<String, String>(props)) {
                for (int i = 750000; i < 1000000; i++) {
                    producer.send(new ProducerRecord<String, String>("device", Integer.toString(i), json));
                }
            } finally {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        System.out.println(new Date()+"完成发送");
        executorService.shutdown();
    }

}
