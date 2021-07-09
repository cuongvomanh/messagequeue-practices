package com.example.messagequeueinspring.messagequeue.comsumer.impl;

import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumerRecord;
import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumerRecords;
import org.jetbrains.annotations.NotNull;

import javax.jms.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ActiveMQConsumerRecordsAdapter<K, V> implements MessageConsumerRecords<K, V> {
    private List<MessageConsumerRecord<K,V>> consumerRecords = new ArrayList<>();

    @NotNull
    @Override
    public Iterator<MessageConsumerRecord<K, V>> iterator() {
        return consumerRecords.iterator();
    }

    public void setConsumerRecords(List<Message> records) {
        for (Message record: records){
            ActiveMQConsumerRecordAdapter consumerRecord = new ActiveMQConsumerRecordAdapter();
            consumerRecord.setConsumerRecord(record);
            consumerRecords.add(consumerRecord);
        }
    }

    @Override
    public void forEach(Consumer<? super MessageConsumerRecord<K, V>> action) {
        MessageConsumerRecords.super.forEach(action);
    }

    @Override
    public Spliterator<MessageConsumerRecord<K, V>> spliterator() {
        return MessageConsumerRecords.super.spliterator();
    }
}
