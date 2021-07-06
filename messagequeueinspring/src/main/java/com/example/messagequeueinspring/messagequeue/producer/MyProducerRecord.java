package com.example.messagequeueinspring.messagequeue.producer;

import com.example.messagequeueinspring.dto.BookDTO;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public interface MyProducerRecord {


    public void setTopicsAndBook(String topics, BookDTO book);


    public ProducerRecord getProducerRecord();
}
