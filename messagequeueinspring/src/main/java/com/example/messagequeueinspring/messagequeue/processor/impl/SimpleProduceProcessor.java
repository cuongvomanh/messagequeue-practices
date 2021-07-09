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
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
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
            producerRecord.setTopicsAndBook(topics, book);
            messageProducer.send(producerRecord, printSendResultCallback());
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
