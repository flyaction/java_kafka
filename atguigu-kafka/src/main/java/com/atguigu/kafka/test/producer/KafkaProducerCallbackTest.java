package com.atguigu.kafka.test.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author: action
 * @create: 2025/4/8 14:25
 **/
public class KafkaProducerCallbackTest {
    public static void main(String[] args) throws Exception {

        //创建配置对象
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //对生产的数据K,V进行序列化的操作
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //ACK应答模式 0 1 all
        configMap.put(ProducerConfig.ACKS_CONFIG,"0");

        //创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String,String>(configMap);
        //创建数据
        //构建数据时需要传递三个参数，第一个表示主题，第二个表示key，第三个表示value
        //ProducerRecord<String,String> record = new ProducerRecord<String, String>("test","key","value");
        //通过生产者对象将数据发送到kafka
        //producer.send(record);
        for (int i = 0; i < 10; i++){
            ProducerRecord<String,String> record = new ProducerRecord<String, String>("test1","key"+i,"value"+i);
            //异步发送
            final Future<RecordMetadata> send = producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println("数据发送成功：" + recordMetadata);
                }
            });
            System.out.println("发送数据");
            send.get();
        }



        //关闭生产者
        producer.close();


    }
}
