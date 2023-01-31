package com.nowcoder.community.event;

import com.alibaba.fastjson.JSONObject;
import com.nowcoder.community.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2023-01-02 09:24
 **/

@Component
public class EventProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    //处理事件
    public void fireEvent(Event event){
        //将事件发布到指定的主题
        //生产者所发送的消息是json字符串
        kafkaTemplate.send(event.getTopic(), JSONObject.toJSONString(event));
    }
}