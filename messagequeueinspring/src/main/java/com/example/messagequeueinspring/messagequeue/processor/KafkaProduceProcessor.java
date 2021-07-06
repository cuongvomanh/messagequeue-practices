package com.example.messagequeueinspring.messagequeue.processor;

import com.example.messagequeueinspring.config.KafkaProperties;
import com.example.messagequeueinspring.dto.BookDTO;
import com.example.messagequeueinspring.messagequeue.producer.MyKafkaProducer;
import com.example.messagequeueinspring.messagequeue.producer.MyProducerRecord;
import com.example.messagequeueinspring.messagequeue.producer.Producer;
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
@Scope("prototype")
@Profile("kafka & messagequeuetest")
public class KafkaProduceProcessor implements ProduceProcessorTemplate {
    private Logger LOGGER = LoggerFactory.getLogger(KafkaProduceProcessor.class);
    @Autowired
    private KafkaProperties kafkaProperties;
    private String topics;
    @Autowired
    private Producer producer;
    @Autowired
    private ApplicationContext context;

    @Override
    public void run() {
        try {
            BookDTO book = new BookDTO(1, "Harry Potter", 0);
            MyProducerRecord producerRecord = (MyProducerRecord) context.getBean(MyProducerRecord.class);
            producerRecord.setTopicsAndBook(topics, book);
            producer.send(producerRecord, printSendResultCallback());
//            KafkaProducer producer = new KafkaProducer<>(kafkaProperties.getProducerProps());
//            Thread.sleep(5000L);
//            producer.send(new ProducerRecord(topics, book), printSendResultCallback());
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
