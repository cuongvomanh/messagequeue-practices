package com.example.messagequeueinspring.config.messagequeue;

import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumer;
import com.example.messagequeueinspring.messagequeue.comsumer.impl.ActiveMQConsumerAdapter;
import com.example.messagequeueinspring.messagequeue.comsumer.impl.KafkaConsumerAdapter;
import com.example.messagequeueinspring.messagequeue.producer.MessageProducer;
import com.example.messagequeueinspring.messagequeue.producer.MessageProducerRecord;
import com.example.messagequeueinspring.messagequeue.producer.impl.ActiveMQProducerAdapter;
import com.example.messagequeueinspring.messagequeue.producer.impl.ActiveMQProducerRecordAdapter;
import com.example.messagequeueinspring.messagequeue.producer.impl.KafkaProducerAdapter;
import com.example.messagequeueinspring.messagequeue.producer.impl.KafkaProducerRecordAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

public interface MessageConfiguration {
    @Bean
    public MessageProducer messageProducer();

    @Bean
    @Scope("prototype")
    public MessageProducerRecord messageProducerRecord();

    @Bean
    public MessageConsumer messageConsumer();
}
