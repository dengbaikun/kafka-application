package com.dk.listener;

import com.alibaba.fastjson.JSON;
import com.itheima.demo.config.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;

/*
* 顺序性消费
* 阻塞队列 + 多线程实现
* */
@Component
public class SortedConsumer {
    private final Logger logger = LoggerFactory.getLogger(SortedConsumer.class);
    //队列数量，根据业务情况决定
    //本案例，队列=4，线程=4，一一对应
    LinkedBlockingQueue[] queues = new LinkedBlockingQueue[4];

    @PostConstruct
    void execute(){
        //遍历队列数组，初始化每一个元素，同时让线程启动
        for (int i = 0; i < 4; i++) {
            logger.info("thread started , index = {}", i);
            final int current = i;
            queues[current] = new LinkedBlockingQueue();
            new Thread( () -> {
                try {
                    //循环4次，起4个线程一直监听自己的队列，不停获取数据
                    //取到就执行打印log，取不到阻塞等待
                    while (true) {
                        Order order = (Order) queues[current].take();
                        logger.info("get from queue, queue:{},order:[id={},status={}]",
                                current,
                                order.getId(),
                                order.getStatus());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    @KafkaListener(topics = {"sorted"},topicPattern = "0",groupId = "sorted-group-1")
    public void onMessage(ConsumerRecord<?, ?> consumerRecord) throws InterruptedException {
        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
        if (optional.isPresent()) {
            Object msg = optional.get();
//            从kafka里获取到消息
//            注意分发方式，kafka有两个分区0和1，对应两个消费者
//            当前分区是0，也就是偶数的id，0、2、4、6会在这里被消费
            Order order = JSON.parseObject(String.valueOf(msg),Order.class);


//            而队列是4个。也就是每个消费者再分到两个队列里去
//            队列另一端分别对应4个线程在等待
//            所以，按4取余数
            int index = order.getId() % 4;
//            logger.info("put to queue,queue={},order:[id={},status={}]",index,order.getId(),order.getStatus());
            queues[index].put(order);
        }
    }

    @KafkaListener(topics = {"sorted"},topicPattern = "1",groupId = "sorted-group-1")
    public void onMessage1(ConsumerRecord<?, ?> consumerRecord) throws InterruptedException {
        //相同的实现，现实中为另一台机器，这里用两个listener模拟
        //奇数的id会被分到这里，也就是1、3、5、7
        onMessage(consumerRecord);
    }
}