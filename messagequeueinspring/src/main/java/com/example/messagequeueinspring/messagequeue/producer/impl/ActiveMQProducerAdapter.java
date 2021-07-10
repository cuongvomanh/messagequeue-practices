package com.example.messagequeueinspring.messagequeue.producer.impl;

import com.example.messagequeueinspring.config.ActiveMQProperties;
import com.example.messagequeueinspring.messagequeue.producer.MessageProducer;
import com.example.messagequeueinspring.messagequeue.producer.MessageProducerRecord;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.kafka.clients.producer.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.jms.*;
import java.io.Serializable;

public class ActiveMQProducerAdapter implements MessageProducer {
    private Logger LOGGER = LoggerFactory.getLogger(ActiveMQProducerAdapter.class);
    @Autowired
    private ActiveMQProperties activeMQProperties;
    private Session session;

    @PostConstruct
    public void initKafkaProducer() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(activeMQProperties.getUrl());
        Connection connection = null;
        connection = connectionFactory.createConnection(activeMQProperties.getUserName(), activeMQProperties.getPassword());
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

    }

    @Override
    public void send(MessageProducerRecord producerRecord, Callback printSendResultCallback) {
        try {
            Destination destination = session.createQueue(producerRecord.getTopics());
            javax.jms.MessageProducer producer = session.createProducer(destination);
            if (producerRecord.getSendObject() instanceof Serializable){
                Message message = session.createObjectMessage((Serializable) producerRecord.getSendObject());
//                TextMessage message = session.createTextMessage(msg);
                producer.send(message);
                LOGGER.info("'" + producerRecord.getSendObject() + "'' has been send.");
            } else {
                throw new RuntimeException("Send Object must has Serializable");
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
