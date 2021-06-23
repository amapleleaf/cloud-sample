package com.sample.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Random;


@Component
public class MyKafkaProducer {
    private ObjectMapper mapper = new ObjectMapper();
    private static  final Logger log = LoggerFactory.getLogger(MyKafkaProducer.class);
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    //自定义topic
    public static final String TOPIC_TEST = "topic_test";

    //
    public static final String TOPIC_GROUP = "topic_group";

    //
    public static final String TOPIC_GROUP2 = "topic_group2";

    public void send(Object obj) {
        try {
            for(int i=0;i<5000;i++) {
                String obj2String = mapper.writeValueAsString(obj);
                //log.info("准备发送消息为：{}", obj2String+"["+i+"]");
                //发送消息
                ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_TEST, obj);
                future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        //发送失败的处理
                        log.info(TOPIC_TEST + " - 生产者 发送消息失败：" + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                        //成功的处理
                        log.info(TOPIC_TEST + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
                    }
                });
                try {
                    Thread.sleep(getRandomNumberInRange(60,500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }

    private  int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}