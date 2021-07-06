package com.example.messagequeueinspring.messagequeue.producer;

import com.example.messagequeueinspring.dto.BookDTO;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MyKafkaProducerRecord implements MyProducerRecord {

    private ProducerRecord producerRecord;
    private String topics;
    private BookDTO book;

    @Override
    public void setTopicsAndBook(String topics, BookDTO book) {
        this.topics = topics;
        producerRecord = new ProducerRecord(topics, book);
    }


    public ProducerRecord getProducerRecord() {
        return producerRecord;
    }
}
