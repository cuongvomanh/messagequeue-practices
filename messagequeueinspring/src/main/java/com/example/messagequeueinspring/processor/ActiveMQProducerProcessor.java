package com.example.messagequeueinspring.processor;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.jms.*;

public class ActiveMQProducerProcessor implements Processor{
    private Logger LOGGER = LoggerFactory.getLogger(ActiveMQProducerProcessor.class);
    private String subject = "TESTQUEUE1";

    @Override
    public void run() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection("admin", "admin");
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(subject);
            MessageProducer producer = session.createProducer(destination);
            try {
                int msgTemp = 0;
                while(true){
                    msgTemp ++;
                    String msg = "TextMessage-" + String.valueOf(msgTemp);
                    TextMessage message = session.createTextMessage(msg);
                    producer.send(message);
                    LOGGER.info("'" + msg + "'' has been send.");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e){
                LOGGER.error("Error when during sent message: ");
                e.printStackTrace();
            }
        } catch (JMSException e) {
            LOGGER.error("Error thread activemq produce");
            e.printStackTrace();
        }
    }
}
