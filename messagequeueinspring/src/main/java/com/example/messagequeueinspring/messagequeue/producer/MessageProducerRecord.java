package com.example.messagequeueinspring.messagequeue.producer;

public interface MessageProducerRecord<K, V> {


    public void setTopicsAndSendObject(String topics, V book);


    public Object getProducerRecord();

    public String getTopics();
}
