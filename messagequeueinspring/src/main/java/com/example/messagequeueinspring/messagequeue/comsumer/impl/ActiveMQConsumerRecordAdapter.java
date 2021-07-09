package com.example.messagequeueinspring.messagequeue.comsumer.impl;

import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumerRecord;
import org.apache.activemq.command.ActiveMQObjectMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.Serializable;

public class ActiveMQConsumerRecordAdapter<K, V> implements MessageConsumerRecord<K, V> {
    private V record;

    @Override
    public V value() {
        if (record instanceof ActiveMQObjectMessage){
            Serializable serializable = null;
            try {
                serializable = ((ActiveMQObjectMessage) record).getObject();
                return (V) serializable;
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("Incorrect Serializable.");
        }
        return null;
    }

    public void setConsumerRecord(V record) {
        this.record = record;
    }
}
