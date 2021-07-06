package com.example.messagequeueinspring.messagequeue.producer;

import org.apache.kafka.clients.producer.Callback;

public interface Producer {
    void send(MyProducerRecord producerRecord, Callback printSendResultCallback);
}
