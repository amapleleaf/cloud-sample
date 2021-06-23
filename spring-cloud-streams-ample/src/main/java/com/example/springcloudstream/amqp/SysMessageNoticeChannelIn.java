package com.example.springcloudstream.amqp;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface SysMessageNoticeChannelIn {
    /**
     * 消费者消息输入通道（需要与配置文件中的保持一致）
     */
    String INPUT = "sysMssageNoticeInput";

    /**
     * 消息消费
     *
     * @return
     */
    @Input(INPUT)
    SubscribableChannel sysMssageNoticeInput();
}
