package com.example.springcloudstream.amqp;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EnableBinding(SysMessageNoticeChannelIn.class)
public class DefaultMessageConsumer {
    @StreamListener(SysMessageNoticeChannelIn.INPUT)
    public void recevieMes(Message<Map<String,Object>> message){
        System.out.println("------------------消息："+message);
        System.out.println("------------------消息载体："+message.getPayload());
    }
}
