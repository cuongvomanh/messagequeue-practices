package com.example.messagequeueinspring.config.messagequeue.impl;

import com.example.messagequeueinspring.config.messagequeue.MessageConfiguration;
import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumer;
import com.example.messagequeueinspring.messagequeue.comsumer.impl.ActiveMQConsumerAdapter;
import com.example.messagequeueinspring.messagequeue.producer.MessageProducer;
import com.example.messagequeueinspring.messagequeue.producer.MessageProducerRecord;
import com.example.messagequeueinspring.messagequeue.producer.impl.ActiveMQProducerAdapter;
import com.example.messagequeueinspring.messagequeue.producer.impl.ActiveMQProducerRecordAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

@Configuration
@Profile("activemq")
public class ActiveMQConfiguration implements MessageConfiguration {
    @Bean
    @Override
    public MessageProducer messageProducer() {
        return new ActiveMQProducerAdapter();
    }

    @Bean
    @Override
    @Scope("prototype")
    public MessageProducerRecord messageProducerRecord() {
        return new ActiveMQProducerRecordAdapter();
    }

    @Bean
    @Override
    public MessageConsumer messageConsumer() {
        return new ActiveMQConsumerAdapter();
    }
}
