package com.example.messagequeueinspring.messagequeue.producer;

import org.apache.kafka.clients.producer.Callback;

public interface MessageProducer {
    void send(MessageProducerRecord producerRecord, Callback printSendResultCallback);
}
