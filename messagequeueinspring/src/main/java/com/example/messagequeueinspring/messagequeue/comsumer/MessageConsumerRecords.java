package com.example.messagequeueinspring.messagequeue.comsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface MessageConsumerRecords<K,V> extends Iterable<MessageConsumerRecord<K, V>> {
}
