package com.example.messagequeueinspring.messagequeue.comsumer.impl;

import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.utils.AbstractIterator;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class KafkaConsumerRecordsAdapter<K,V> implements MessageConsumerRecords {
    private ConsumerRecords consumerRecords;
    public KafkaConsumerRecordsAdapter() {
    }

    @NotNull
    @Override
    public Iterator iterator() {
        return (new KafkaConsumerRecordsAdapter.ConcatenatedIterable(consumerRecords.iterator())).iterator();
    }

    public void setConsumerRecords(ConsumerRecords records) {
        this.consumerRecords = records;
    }

    private static class ConcatenatedIterable<K, V> implements Iterable<KafkaConsumerRecordAdapter<K, V>> {
        private final Iterator iterables;

        public ConcatenatedIterable(Iterator iterables) {
            this.iterables = iterables;
        }

        public Iterator<KafkaConsumerRecordAdapter<K, V>> iterator() {
            return new AbstractIterator<KafkaConsumerRecordAdapter<K, V>>() {
                Iterator<ConsumerRecord<K, V>> iters;
                {
                    this.iters = KafkaConsumerRecordsAdapter.ConcatenatedIterable.this.iterables;
                }
                public KafkaConsumerRecordAdapter<K, V> makeNext() {
                    if (!this.iters.hasNext()) {
                        return (KafkaConsumerRecordAdapter) this.allDone();
                    }
                    KafkaConsumerRecordAdapter kafkaConsumerRecordAdapter = new KafkaConsumerRecordAdapter<>();
                    kafkaConsumerRecordAdapter.setConsumerRecord(this.iters.next());
                    return kafkaConsumerRecordAdapter;
                }
            };
        }
    }
}
