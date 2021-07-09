package com.example.messagequeueinspring.config.messagequeue.impl;

import com.example.messagequeueinspring.config.messagequeue.MessageConfiguration;
import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumer;
import com.example.messagequeueinspring.messagequeue.comsumer.impl.KafkaConsumerAdapter;
import com.example.messagequeueinspring.messagequeue.producer.MessageProducer;
import com.example.messagequeueinspring.messagequeue.producer.MessageProducerRecord;
import com.example.messagequeueinspring.messagequeue.producer.impl.KafkaProducerAdapter;
import com.example.messagequeueinspring.messagequeue.producer.impl.KafkaProducerRecordAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

@Configuration
@Profile("kafka")
public class KafkaConfiguration implements MessageConfiguration {
    @Bean
    @Override
    public MessageProducer messageProducer() {
        return new KafkaProducerAdapter();
    }

    @Bean
    @Override
    @Scope("prototype")
    public MessageProducerRecord messageProducerRecord() {
        return new KafkaProducerRecordAdapter();
    }

    @Bean
    @Override
    public MessageConsumer messageConsumer() {
        return new KafkaConsumerAdapter();
    }
}
