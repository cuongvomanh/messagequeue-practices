package com.example.messagequeueinspring.messagequeue.producer.impl;

import com.example.messagequeueinspring.config.KafkaProperties;
import com.example.messagequeueinspring.messagequeue.producer.MessageProducer;
import com.example.messagequeueinspring.messagequeue.producer.MessageProducerRecord;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class KafkaProducerAdapter implements MessageProducer {
    private Logger LOGGER = LoggerFactory.getLogger(KafkaProducerAdapter.class);
    @Autowired
    private KafkaProperties kafkaProperties;
    private KafkaProducer producer;

    public KafkaProducerAdapter() {
    }

    @PostConstruct
    public void initKafkaProducer(){
        producer = new KafkaProducer<>(kafkaProperties.getProducerProps());
    }

    @Override
    public void send(MessageProducerRecord producerRecord, Callback printSendResultCallback) {
        if (producerRecord instanceof KafkaProducerRecordAdapter){
            ProducerRecord producerRecord1 = ((KafkaProducerRecordAdapter) producerRecord).getSendObject();
            producer.send(producerRecord1, printSendResultCallback);
            LOGGER.info("'" + producerRecord1.value() + "'' has been send.");
        } else {
            throw new RuntimeException("Incorrect type of MessageProducerRecord");
        }

    }
}
