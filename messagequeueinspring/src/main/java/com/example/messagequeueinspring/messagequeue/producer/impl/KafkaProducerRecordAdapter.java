package com.example.messagequeueinspring.messagequeue.producer.impl;

import com.example.messagequeueinspring.messagequeue.producer.MessageProducerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerRecordAdapter<K, V> implements MessageProducerRecord<K, V> {

    private ProducerRecord producerRecord;
    private String topics;

    @Override
    public void setTopicsAndSendObject(String topics, V book) {
        this.topics = topics;
        producerRecord = new ProducerRecord<String, V>(topics, book);
    }

    @Override
    public String getTopics() {
        return topics;
    }

    public ProducerRecord getProducerRecord() {
        return producerRecord;
    }
}
