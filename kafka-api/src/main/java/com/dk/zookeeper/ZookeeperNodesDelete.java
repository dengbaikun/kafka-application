package com.dk.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author DK
 * @version 1.0
 * @date 2020/11/5 11:49 下午
 */
public class ZookeeperNodesDelete {

    // 临时解决节点已存在的问题
    public static void main(String[] args) throws Exception {

        RetryPolicy retryPolicy  = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.32.5:2181,192.168.32.6:2181,192.168.32.7:2181")
                .sessionTimeoutMs(3000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();

        client.start();

        try {
            client.delete().deletingChildrenIfNeeded().forPath("/brokers");
        }catch (Exception e){

        }

        try {
            client.delete().deletingChildrenIfNeeded().forPath("/admin");
        }catch (Exception e){

        }

        try {
            client.delete().deletingChildrenIfNeeded().forPath("/cluster");
        }catch (Exception e){

        }
        try {
            client.delete().deletingChildrenIfNeeded().forPath("/config");
        }catch (Exception e){

        }
        try {
            client.delete().deletingChildrenIfNeeded().forPath("/consumers");
        }catch (Exception e){

        }
        try {
            client.delete().deletingChildrenIfNeeded().forPath("/controller_epoch");
        }catch (Exception e){

        }
        try {
            client.delete().deletingChildrenIfNeeded().forPath("/isr_change_notification");
        }catch (Exception e){

        }
        try {
            client.delete().deletingChildrenIfNeeded().forPath("/latest_producer_id_block");
        }catch (Exception e){

        }
        try {
            client.delete().deletingChildrenIfNeeded().forPath("/log_dir_event_notification");
        }catch (Exception e){

        }

    }

}
