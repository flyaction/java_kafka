package com.atguigu.kafka.test.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author: action
 * @create: 2025/4/8 14:25
 **/
public class KafkaProducerITransactionTest {
    public static void main(String[] args) throws Exception {

        //创建配置对象
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //对生产的数据K,V进行序列化的操作
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //ACK应答模式 0 1 all(-1)
        configMap.put(ProducerConfig.ACKS_CONFIG,"-1");
        //幂等性要等ack=-1,并开启重试机制，在途缓冲区数量不能大于5
        configMap.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,true);
        configMap.put(ProducerConfig.RETRIES_CONFIG,5);
        configMap.put(ProducerConfig.BATCH_SIZE_CONFIG,5);
        configMap.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG,3000);
        //事务
        configMap.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG,"my-tx-id");


        //创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String,String>(configMap);

        //初始化事务
        producer.initTransactions();

        try {
            //开启事务
            producer.beginTransaction();

            //创建数据
            //构建数据时需要传递三个参数，第一个表示主题，第二个表示key，第三个表示value
            //ProducerRecord<String,String> record = new ProducerRecord<String, String>("test","key","value");
            //通过生产者对象将数据发送到kafka
            //producer.send(record);
            for (int i = 0; i < 20; i++){
                ProducerRecord<String,String> record = new ProducerRecord<String, String>("test1","key"+i,"value"+i);
                //异步发送
                final Future<RecordMetadata> send = producer.send(record);

            }

            //提交事务
            producer.commitTransaction();

        }catch (Exception e){
            e.printStackTrace();
            producer.abortTransaction();
        }finally {
            //关闭生产者
            producer.close();
        }








    }
}
