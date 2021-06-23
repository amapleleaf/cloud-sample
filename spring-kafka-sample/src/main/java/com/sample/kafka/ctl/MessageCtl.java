package com.sample.kafka.ctl;

import com.sample.kafka.producer.MyKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageCtl {
    @Autowired
    private MyKafkaProducer myKafkaProducer;

    @RequestMapping("/send")
    public void sendMsg(){
        myKafkaProducer.send("你好！！！");
    }
}
