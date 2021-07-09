package com.example.messagequeueinspring.messagequeue.comsumer;

public interface MessageConsumerRecord<K,V> {
    public V value();
}
