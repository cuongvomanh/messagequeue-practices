package com.example.messagequeueinspring.messagequeue.producer.impl;

import com.example.messagequeueinspring.dto.BookDTO;
import com.example.messagequeueinspring.messagequeue.producer.MessageProducerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
//@Scope("prototype")
public class KafkaProducerRecordAdapter<K, V> implements MessageProducerRecord<K, V> {

    private ProducerRecord producerRecord;
    private String topics;
    private BookDTO book;

    @Override
    public void setTopicsAndBook(String topics, V book) {
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
