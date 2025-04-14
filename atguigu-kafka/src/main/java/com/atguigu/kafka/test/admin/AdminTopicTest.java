package com.atguigu.kafka.test.admin;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.*;

/**
 * @author: action
 * @create: 2025/4/14 11:31
 **/
public class AdminTopicTest {
    public static void main(String[] args) {

        Map<String, Object> configMap = new HashMap<>();
        configMap.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        //管理员对象
        final Admin admin = Admin.create(configMap);

        //构建主题 主题名称+分区数量+主题分区副本的数量
        String topicName = "test1";
        int partitionCount = 1;
        short replicationCount = 1;
        NewTopic topic1 = new NewTopic(topicName, partitionCount, replicationCount);

        String topicName1 = "test2";
        int partitionCount1 = 2;
        short replicationCount1 = 2;
        NewTopic topic2 = new NewTopic(topicName1, partitionCount1, replicationCount1);


        String topicName2 = "test4";
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0,Arrays.asList(3,1));
        map.put(1,Arrays.asList(2,3));
        map.put(2,Arrays.asList(1,2));

        NewTopic topic3 = new NewTopic(topicName2,map);

        //创建主题
        admin.createTopics(
                Arrays.asList(topic3)
        );

        // In - Sync - Replicas  同步副本列表(ISR)

        //关闭
        admin.close();
    }
}
