package com.example.messagequeueinspring.messagequeue.processor;

import com.example.messagequeueinspring.config.ActiveMQProperties;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
@Scope("prototype")
@Profile("activemq & messagequeuetest")
public class ActiveMQProducerProcessor implements ProduceProcessorTemplate{
    private Logger LOGGER = LoggerFactory.getLogger(ActiveMQProducerProcessor.class);
    private ActiveMQProperties activeMQProperties;
    private String topics;

    public ActiveMQProducerProcessor(ActiveMQProperties activeMQProperties) {
        this.activeMQProperties = activeMQProperties;
    }

    @Override
    public void run() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(activeMQProperties.getUrl());
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection(activeMQProperties.getUserName(), activeMQProperties.getPassword());
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(topics);
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

    @Override
    public void setTopics(String topics) {
        this.topics = topics;
    }
}
