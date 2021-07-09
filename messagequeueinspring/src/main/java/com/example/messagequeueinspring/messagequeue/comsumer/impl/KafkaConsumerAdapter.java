package com.example.messagequeueinspring.messagequeue.comsumer.impl;

import com.example.messagequeueinspring.config.KafkaProperties;
import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumer;
import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;

public class KafkaConsumerAdapter<K,V> implements MessageConsumer {
    @Autowired
    private KafkaProperties kafkaProperties;
    private KafkaConsumer consumer;
    private String topics;

    @PostConstruct
    public void setup(){
        Map<String, Object> consumerProps = kafkaProperties.getConsumerProps();
        consumer = new KafkaConsumer<>(consumerProps);
    }

    @Override
    public void subscribe(String topics){
        consumer.subscribe(Arrays.asList(topics));
        this.topics = topics;
    }

    @Override
    public KafkaConsumerRecordsAdapter<K, V> poll(Duration duration) {
        KafkaConsumerRecordsAdapter consumerRecordsAdapter = new KafkaConsumerRecordsAdapter();
        consumerRecordsAdapter.setConsumerRecords(consumer.poll(duration));
        return consumerRecordsAdapter;
    }

    @Override
    public void commitSync() {
        consumer.commitSync();
    }

    @Override
    public void close() {
        consumer.close();
    }
}
