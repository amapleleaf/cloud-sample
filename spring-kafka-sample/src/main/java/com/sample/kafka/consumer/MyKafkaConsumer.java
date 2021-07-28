package com.sample.kafka.consumer;

import com.sample.kafka.producer.MyKafkaProducer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MyKafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(MyKafkaConsumer.class);
    @Autowired
    private ConsumerFactory consumerFactory;
    //@KafkaListener(topics = MyKafkaProducer.TOPIC_TEST, groupId = MyKafkaProducer.TOPIC_GROUP)
    public void topic_test(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
        try {
            System.out.println("--------------------------------------------------");
            log.info("Id0 records size " +  records.size());
            for  (ConsumerRecord<?, ?> record : records){
                Optional message = Optional.ofNullable(record.value());
                if (message.isPresent()) {
                    Object msg = message.get();
                    System.out.println("组:"+MyKafkaProducer.TOPIC_GROUP+"，topic名称:" + record.topic() + "，key:" +
                            record.key() + "，" +
                            "分区位置:" + record.partition()
                            + ", 下标" + record.offset()+",消息:"+message);
                }
            }
            System.out.println("--------------------------------------------------");
        } finally {
            ack.acknowledge();
        }
    }

    @KafkaListener(topics = MyKafkaProducer.TOPIC_TEST, groupId = MyKafkaProducer.TOPIC_GROUP)
    public void topic_test1(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        try {
            Optional message = Optional.ofNullable(record.value());
            if (message.isPresent()) {
                Object msg = message.get();
                System.out.println("组:"+MyKafkaProducer.TOPIC_GROUP+"，topic名称:" + record.topic() + "，key:" +
                        record.key() + "，" +
                        "分区位置:" + record.partition()
                        + ", 下标" + record.offset()+",消息:"+message);
            }
        } finally {
            ack.acknowledge();
        }
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory filterContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        //配合RecordFilterStrategy使用，被过滤的信息将被丢弃
        factory.setAckDiscarded(true);
        factory.setRecordFilterStrategy(consumerRecord -> {
            long data = Long.parseLong((String) consumerRecord.value());
            log.info("filterContainerFactory filter : "+data);
            if (data % 2 == 0) {
                return false;
            }
            //返回true将会被丢弃
            return true;
        });
        return factory;
    }

}
