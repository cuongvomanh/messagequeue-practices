package com.example.messagequeueinspring.messagequeue.comsumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;

import javax.jms.Message;
import java.time.Duration;

public interface MessageConsumer {
    <V> MessageConsumerRecords<String,V> poll(Duration duration);

    public void subscribe(String topics);

    void commitSync();

    void close();
}
