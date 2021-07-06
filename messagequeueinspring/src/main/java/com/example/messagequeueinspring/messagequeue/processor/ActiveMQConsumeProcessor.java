package com.example.messagequeueinspring.messagequeue.processor;

import com.example.messagequeueinspring.config.ActiveMQProperties;
import com.example.messagequeueinspring.service.handler.Handler;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
@Scope("prototype")
@Profile("activemq")
public class ActiveMQConsumeProcessor implements ConsumeProcessorTemplate {
    private Logger LOGGER = LoggerFactory.getLogger(ActiveMQConsumeProcessor.class);

    private ActiveMQProperties activeMQProperties;

    public ActiveMQConsumeProcessor(ActiveMQProperties activeMQProperties) {
        this.activeMQProperties = activeMQProperties;
    }

    @Override
    public void run() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(activeMQProperties.getUrl());
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(activeMQProperties.getQueueName());
            MessageConsumer consumer = session.createConsumer(destination);
            while (true) {
                Message message = consumer.receive();
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    LOGGER.info("Received message '" + textMessage.getText() + "'");
                }
            }
        } catch (JMSException e) {
            LOGGER.error("Error thread activemq consumer");
            e.printStackTrace();
        }
    }


    @Override
    public void setTopics(String topics) {

    }

    @Override
    public void setHandler(Handler handler) {

    }
}
