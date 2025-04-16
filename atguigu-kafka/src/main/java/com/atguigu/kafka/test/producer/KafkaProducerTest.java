package com.atguigu.kafka.test.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: action
 * @create: 2025/4/8 14:25
 **/
public class KafkaProducerTest {
    public static void main(String[] args) throws Exception{

        //创建配置对象
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //对生产的数据K,V进行序列化的操作
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String,String>(configMap);
        //创建数据
        //构建数据时需要传递三个参数，第一个表示主题，第二个表示key，第三个表示value
        //ProducerRecord<String,String> record = new ProducerRecord<String, String>("test","key","value");
        //通过生产者对象将数据发送到kafka
        //producer.send(record);
        for (int i = 0; i < 1000; i++){
            ProducerRecord<String,String> record = new ProducerRecord<String, String>("test","key"+i,"value"+i);
            producer.send(record);
            Thread.sleep(1000);
        }



        //关闭生产者
        producer.close();


    }
}
