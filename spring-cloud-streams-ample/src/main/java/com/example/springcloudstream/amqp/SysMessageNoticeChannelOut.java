package com.example.springcloudstream.amqp;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SysMessageNoticeChannelOut {
    /**
     * 生产者消息输出通道（需要与配置文件中的保持一致）
     */
    String OUTPUT = "sysMssageNoticeOutput";

    /**
     * 消息生产
     *
     * @return
     */
    @Output(OUTPUT)
    MessageChannel sysMssageNoticeOut();
}
