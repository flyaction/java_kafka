package org.atguigu.springkafka.component;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.atguigu.springkafka.config.SpringBootKafkaConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: action
 * @create: 2025/4/17 14:47
 **/
@Component
@Slf4j
public class KafkaDataConsumer {

    @KafkaListener(topics = SpringBootKafkaConfig.TOPIC_TEST,groupId = SpringBootKafkaConfig.GROUP_ID)
    public void consume(List<String> messages, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        for (String message : messages){
            final JSONObject entries = JSONUtil.parseObj(message);
            System.out.println(SpringBootKafkaConfig.GROUP_ID+" 消费了 : Topic：" + topic + ",Message: " + entries.getStr("data"));

        }
    }
}
