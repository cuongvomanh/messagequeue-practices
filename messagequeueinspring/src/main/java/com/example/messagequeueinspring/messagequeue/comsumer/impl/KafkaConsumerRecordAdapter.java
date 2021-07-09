package com.example.messagequeueinspring.messagequeue.comsumer.impl;

import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class KafkaConsumerRecordAdapter<K,V> implements MessageConsumerRecord<K,V> {
    private ConsumerRecord<K,V> consumerRecord;
    public KafkaConsumerRecordAdapter() {
    }

    public void setConsumerRecord(ConsumerRecord<K,V> consumerRecord){
        this.consumerRecord = consumerRecord;
    }

    @Override
    public V value() {
        return consumerRecord.value();
    }
}
