package com.atguigu.kafka.test.producer;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author: action
 * @create: 2025/4/14 16:12
 **/
public class ValueInterceptorTest implements ProducerInterceptor<String, String> {

    //发送数据的时候，会调用此方法
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        return new ProducerRecord<String, String>(producerRecord.topic(), producerRecord.key(), producerRecord.value() + "---interceptor");
    }

    //发送数据完毕，服务器返回的响应，会调用此方法
    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }

    //生产者对象关闭的时候，会调用此方法
    @Override
    public void close() {

    }

    //创建生产者对象的时候，会调用此方法
    @Override
    public void configure(Map<String, ?> map) {

    }
}
