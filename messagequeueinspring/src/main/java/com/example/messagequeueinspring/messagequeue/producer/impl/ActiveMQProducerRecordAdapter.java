package com.example.messagequeueinspring.messagequeue.producer.impl;

import com.example.messagequeueinspring.messagequeue.producer.MessageProducerRecord;

public class ActiveMQProducerRecordAdapter implements MessageProducerRecord {
    private Object producerRecord;
    private String topics;
    @Override
    public void setTopicsAndSendObject(String topics, Object producerRecord) {
        this.topics = topics;
        this.producerRecord = producerRecord;
    }

    @Override
    public Object getProducerRecord() {
        return producerRecord;
    }

    @Override
    public String getTopics() {
        return topics;
    }
}
