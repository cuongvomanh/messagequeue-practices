package com.example.messagequeueinspring.messagequeue.comsumer.impl;

import com.example.messagequeueinspring.config.ActiveMQProperties;
import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumer;
import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumerRecords;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.*;
import java.time.Duration;
import java.util.Arrays;

public class ActiveMQConsumerAdapter<K,V> implements MessageConsumer {
    @Autowired
    private ActiveMQProperties activeMQProperties;
    private String topics;
    private javax.jms.MessageConsumer consumer;

    @Override
    public MessageConsumerRecords<K, V> poll(Duration duration) {
        try {
            Message message = consumer.receive();
            ActiveMQConsumerRecordsAdapter consumerRecords = new ActiveMQConsumerRecordsAdapter();
            consumerRecords.setConsumerRecords(Arrays.asList(message));
            return consumerRecords;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return new ActiveMQConsumerRecordsAdapter();
    }

    @Override
    public void subscribe(String topics) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(activeMQProperties.getUrl());
        connectionFactory.setTrustedPackages(activeMQProperties.getTrustedPackages());
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(topics);
            consumer = session.createConsumer(destination);
            this.topics = topics;
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commitSync() {

    }

    @Override
    public void close() {

    }
}
