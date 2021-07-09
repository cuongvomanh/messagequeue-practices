package com.example.messagequeueinspring.messagequeue.producer;

import com.example.messagequeueinspring.dto.BookDTO;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public interface MessageProducerRecord<K, V> {


    public void setTopicsAndBook(String topics, V book);


    public Object getProducerRecord();

    public String getTopics();
}
