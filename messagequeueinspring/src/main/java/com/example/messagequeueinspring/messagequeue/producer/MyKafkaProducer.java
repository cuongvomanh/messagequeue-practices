package com.example.messagequeueinspring.messagequeue.producer;

import com.example.messagequeueinspring.config.KafkaProperties;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaProducer implements Producer {
    @Autowired
    private KafkaProperties kafkaProperties;
    private KafkaProducer producer;

    public MyKafkaProducer() {
        producer = new KafkaProducer<>(kafkaProperties.getProducerProps());
    }

    public void send(MyProducerRecord producerRecord, Callback printSendResultCallback) {
        producer.send(producerRecord.getProducerRecord(), printSendResultCallback);
    }
}
