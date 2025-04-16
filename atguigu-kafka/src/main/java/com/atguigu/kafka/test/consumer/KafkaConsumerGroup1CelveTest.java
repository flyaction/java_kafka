package com.atguigu.kafka.test.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: action
 * @create: 2025/4/8 14:45
 **/
public class KafkaConsumerGroup1CelveTest {
    public static void main(String[] args) {

        // 创建消费者对象
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG,"atguigu");
        configMap.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG,"aaa");
        //RoundRobinAssignor 轮训策略
        //RangeAssignor 范围策略
        //StickyAssignor 粘性策略
        configMap.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, RoundRobinAssignor.class.getName());
        //configMap.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, RangeAssignor.class.getName());
        //configMap.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, StickyAssignor.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configMap);

        //订阅主题
        consumer.subscribe(Collections.singletonList("test"));

        //从kafka中拉取数据
        while (true){
            final ConsumerRecords<String, String> datas = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> data : datas){
                System.out.println(data.partition());
            }
        }


        //关闭
        //consumer.close();
    }
}
