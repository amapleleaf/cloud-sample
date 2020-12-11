package com.sample.common.model;

import java.io.Serializable;

public class LogMessage  implements Serializable {
    private String messageId;
    private String content;

    public LogMessage(String messageId,String content){
        this.messageId = messageId;
        this.content = content;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "LogMessage{" +
                "messageId='" + messageId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
