package com.sample.common.amqp;

import com.sample.common.model.LogMessage;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogDirectReceiver {

    @RabbitListener(queues = "LogDirectQueue")//监听的队列名称 TestDirectQueue
    @RabbitHandler
    public void process(LogMessage testMessage) {
        System.err.println("---------------------");
        System.err.println("LogDirectQueue消费者收到消息  : " + testMessage);
        System.err.println("---------------------");
    }
}
