package com.example.messagequeueinspring.messagequeue.processor.impl;

import com.example.messagequeueinspring.config.KafkaProperties;
import com.example.messagequeueinspring.dto.BookDTO;
import com.example.messagequeueinspring.messagequeue.processor.ProduceProcessorTemplate;
import com.example.messagequeueinspring.messagequeue.producer.MessageProducer;
import com.example.messagequeueinspring.messagequeue.producer.MessageProducerRecord;
import org.apache.kafka.clients.producer.Callback;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SimpleProduceProcessor implements ProduceProcessorTemplate {
    private Logger LOGGER = LoggerFactory.getLogger(SimpleProduceProcessor.class);
    @Autowired
    private KafkaProperties kafkaProperties;
    private String topics;
    @Autowired
    private MessageProducer messageProducer;
    @Autowired
    private ApplicationContext context;

    @Override
    public void run() {
        try {
            BookDTO book = new BookDTO(1, "Harry Potter", 0);
            MessageProducerRecord<String, BookDTO> producerRecord = (MessageProducerRecord) context.getBean(MessageProducerRecord.class);
            producerRecord.setTopicsAndSendObject(topics, book);

            BookDTO book1 = new BookDTO(1, "Harry Potter1", 0);
            MessageProducerRecord<String, BookDTO> producerRecord1 = (MessageProducerRecord) context.getBean(MessageProducerRecord.class);
            producerRecord1.setTopicsAndSendObject(topics, book1);

            messageProducer.send(producerRecord, printSendResultCallback());
            messageProducer.send(producerRecord1, printSendResultCallback());
        } catch (Exception exception){
            LOGGER.error("Error kafka produce");
            exception.printStackTrace();
        }
    }

    @NotNull
    private Callback printSendResultCallback() {
        return (res, ex) -> {
            if (ex != null) {
                LOGGER.error("Send fail!");
            } else {
                LOGGER.info("Send success!");
            }
        };
    }

    @Override
    public void setTopics(String topics) {
        this.topics = topics;
    }
}
