package com.example.messagequeueinspring.config;

import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumerRecord;
import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumerRecords;
import com.example.messagequeueinspring.messagequeue.comsumer.impl.*;
import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumer;
import com.example.messagequeueinspring.messagequeue.producer.MessageProducerRecord;
import com.example.messagequeueinspring.messagequeue.producer.impl.ActiveMQProducerAdapter;
import com.example.messagequeueinspring.messagequeue.producer.impl.ActiveMQProducerRecordAdapter;
import com.example.messagequeueinspring.messagequeue.producer.impl.KafkaProducerAdapter;
import com.example.messagequeueinspring.messagequeue.producer.MessageProducer;
import com.example.messagequeueinspring.messagequeue.producer.impl.KafkaProducerRecordAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

@Configuration
public class MessageConfiguration {
    @Bean
    @Profile("kafka")
    public MessageProducer KafkaMessageProducer(){
        return new KafkaProducerAdapter();
    }

    @Bean
    @Profile("activemq")
    public MessageProducer activeMQMessageProducer(){
        return new ActiveMQProducerAdapter();
    }

    @Bean
    @Profile("kafka")
    @Scope("prototype")
    public MessageProducerRecord kafkaMessageProducerRecord(){
        return new KafkaProducerRecordAdapter();
    }

    @Bean
    @Profile("activemq")
    @Scope("prototype")
    public MessageProducerRecord activeMQMessageProducerRecord(){
        return new ActiveMQProducerRecordAdapter();
    }

    @Bean
    @Profile("kafka")
    public MessageConsumer kafkaMessageConsumer(){
        return new KafkaConsumerAdapter();
    }

    @Bean
    @Profile("activemq")
    public MessageConsumer activeMQMessageConsumer(){
        return new ActiveMQConsumerAdapter();
    }

    @Bean
    @Profile("kafka")
    public MessageConsumerRecords kafkaMessageConsumerRecords(){
        return new KafkaConsumerRecordsAdapter();
    }

    @Bean
    @Profile("activemq")
    public MessageConsumerRecords activeMQMessageConsumerRecords(){
        return new ActiveMQConsumerRecordsAdapter();
    }

    @Bean
    @Profile("kafka")
    public MessageConsumerRecord kafkaMessageConsumerRecord(){
        return new KafkaConsumerRecordAdapter();
    }

    @Bean
    @Profile("activemq")
    public MessageConsumerRecord activeMQMessageConsumerRecord(){
        return new ActiveMQConsumerRecordAdapter();
    }
}
