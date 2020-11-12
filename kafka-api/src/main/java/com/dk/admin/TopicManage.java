package com.dk.admin;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.ConfigResource;

import java.util.*;

/**
 * @author DK
 * @version 1.0
 * @date 2020/11/6 10:12
 **/
public class TopicManage {
    public static void main(String[] args) throws Exception {
        removeTopic(Arrays.asList("test2","msitemslog","seckilllog"));
//        describeTopics(Collections.singletonList("ser-topic"));
//        updateTopicPartition(Collections.singletonList("ser-topic"), 9);
        listTopicsWithOptions();
    }

    /**
     * 创建一个或多个topic
     * @param topicNames topic名称的集合
     */
    public static void createTopic(List<String> topicNames) throws Exception {
        // 创建AdminClient客户端对象
        AdminClient adminClient = AdminClientFactory.createAdminClientByMap();

        List<NewTopic> topicList = Lists.newArrayList();
        /**
         * 定义topic信息
         * String name                topic名
         * int numPartitions          分区数
         * short replicationFactor    副本数,必须不能大于broker数量
         */
        topicNames.forEach(topicName -> topicList.add(
                new NewTopic(topicName, 9, Short.parseShort("3"))));

        // 创建topic
        CreateTopicsResult result = adminClient.createTopics(topicList);

        // get方法是一个阻塞方法，一定要等到createTopics完成之后才进行下一步操作
        result.all().get();

        // 打印新创建的topic名
        result.values().forEach((name, future) -> System.out.println("topicName:" + name));

        // 关闭资源
        adminClient.close();
    }

    /**
     * 删除一个或多个topic
     *
     * @param topicNames topic名称的集合
     */
    public static void removeTopic(List<String> topicNames) throws Exception {

        // 创建AdminClient客户端对象
        AdminClient adminClient = AdminClientFactory.createAdminClientByProperties();

        // 删除topic集合
        DeleteTopicsResult result = adminClient.deleteTopics(topicNames);

        // get方法是一个阻塞方法，一定要等到deleteTopics完成之后才进行下一步操作
        result.all().get();

        // 关闭资源
        adminClient.close();
    }

    /**
     * 获取所有的topic信息，包括Kafka内部的topic
     * 如：__consumer_offsets，internal=true
     */
    public static void listTopicsWithOptions() throws Exception {
        // 创建AdminClient客户端对象
        AdminClient adminClient = AdminClientFactory.createAdminClientByProperties();

        ListTopicsOptions options = new ListTopicsOptions();
        // 列出内部的Topic
        options.listInternal(true);

        // 列出所有的topic
        ListTopicsResult result = adminClient.listTopics(options);

        // 获取所有topic的名字，返回的是一个Set集合
        Set<String> topicNames = result.names().get();

        // 打印所有topic的名字
        topicNames.forEach(System.out::println);

        // 获取所有topic的信息，返回的是一个Collection集合
        // (name=hello-kafka, internal=false),internal代表是否为内部的topic
        Collection<TopicListing> topicListings = result.listings().get();

        // 打印所有topic的信息
        topicListings.forEach(System.out::println);

        // 关闭资源
        adminClient.close();
    }

    /**
     * 获取topic的描述信息
     *
     * topic name = a-topic, desc = (name=a-topic, internal=false, partitions=(partition=0, leader=192.168.44.160:9092 (id: 0 rack: null), replicas=192.168.44.160:9092 (id: 0 rack: null), isr=192.168.44.160:9092 (id: 0 rack: null)), authorizedOperations=null)
     * topic name = b-topic, desc = (name=b-topic, internal=false, partitions=(partition=0, leader=192.168.44.160:9092 (id: 0 rack: null), replicas=192.168.44.160:9092 (id: 0 rack: null), isr=192.168.44.160:9092 (id: 0 rack: null)), authorizedOperations=null)
     */
    public static void describeTopics(List<String> topics) throws Exception {
        // 创建AdminClient客户端对象
        AdminClient adminClient = AdminClientFactory.createAdminClientByProperties();

        // 获取Topic的描述信息
        DescribeTopicsResult result = adminClient.describeTopics(topics);

        // 解析描述信息结果, Map<String, TopicDescription> ==> topicName:topicDescription
        Map<String, TopicDescription> topicDescriptionMap = result.all().get();
        topicDescriptionMap.forEach((topicName, description) -> System.out.printf("topic name = %s, desc = %s \n", topicName, description));

        // 关闭资源
        adminClient.close();
    }

    /**
     * 获取topic的配置信息
     */
    public static void describeConfigTopics(List<String> topicNames) throws Exception {
        // 创建AdminClient客户端对象
        AdminClient adminClient = AdminClientFactory.createAdminClientByMap();

        List<ConfigResource> configResources = Lists.newArrayListWithCapacity(64);
        topicNames.forEach(topicName -> configResources.add(
                // 指定要获取的源
                new ConfigResource(ConfigResource.Type.TOPIC, topicName)));

        // 获取topic的配置信息
        DescribeConfigsResult result = adminClient.describeConfigs(configResources);

        // 解析topic的配置信息
        Map<ConfigResource, Config> resourceConfigMap = result.all().get();
        resourceConfigMap.forEach((configResource, config) -> System.out.printf("topic config ConfigResource = %s, Config = %s \n", configResource, config));

        // 关闭资源
        adminClient.close();
    }

    /**
     * 修改topic的分区数量
     * 只能增加不能减少
     */
    public static void updateTopicPartition(List<String> topicNames, Integer partitionNum) throws Exception {
        // 创建AdminClient客户端对象
        AdminClient adminClient = AdminClientFactory.createAdminClientByMap();

        // 构建修改分区的topic请求参数
        Map<String, NewPartitions> newPartitions = Maps.newHashMap();
        topicNames.forEach(topicName -> newPartitions.put(topicName, NewPartitions.increaseTo(partitionNum)));

        // 执行修改
        CreatePartitionsResult result = adminClient.createPartitions(newPartitions);

        // get方法是一个阻塞方法，一定要等到createPartitions完成之后才进行下一步操作
        result.all().get();

        // 关闭资源
        adminClient.close();
    }

    /**
     * 修改topic的配置信息
     * 使用旧版api：alterConfigs
     */
    public static void updateTopicConfigOld(List<String> topicNames) throws Exception {
        // 创建AdminClient客户端对象
        AdminClient adminClient = AdminClientFactory.createAdminClientByMap();

        List<ConfigResource> configResources = Lists.newArrayListWithCapacity(64);
        // 指定要修改的ConfigResource类型及名称
        topicNames.forEach(topicName -> configResources.add(new ConfigResource(ConfigResource.Type.TOPIC, topicName)));

        // 建立修改的配置项,配置项以ConfigEntry形式存在
        Config config = new Config(Collections.singletonList(new ConfigEntry("preallocate", "true")));

        // 参数构造
        Map<ConfigResource, Config> configMap = Maps.newHashMap();
        configResources.forEach(configResource -> configMap.put(configResource, config));

        // 修改topic 配置,用的是老api,已经过时
        AlterConfigsResult result = adminClient.alterConfigs(configMap);

        // get方法是一个阻塞方法，一定要等到alterConfigs完成之后才进行下一步操作
        result.all().get();

        // 关闭资源
        adminClient.close();
    }
}
