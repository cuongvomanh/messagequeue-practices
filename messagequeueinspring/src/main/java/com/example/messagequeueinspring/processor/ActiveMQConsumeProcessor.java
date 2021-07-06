package com.example.messagequeueinspring.processor;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class ActiveMQConsumeProcessor implements ConsumeProcessorTemplate {
    private Logger LOGGER = LoggerFactory.getLogger(ActiveMQConsumeProcessor.class);

    private String url = "failover://tcp://localhost:61616";
    private String subject = "TESTQUEUE1";

    @Override
    public void run() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(subject);
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

}
