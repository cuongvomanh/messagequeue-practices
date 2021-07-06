package com.example.messagequeueinspring.messagequeue.processor;

import com.example.messagequeueinspring.config.KafkaProperties;
import com.example.messagequeueinspring.service.handler.Handler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;

@Component
@Scope("prototype")
@Profile("kafka")
public class KafkaConsumeProcessor<T> implements ConsumeProcessorTemplate {
    private final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumeProcessor.class);
    private Handler<T> handler;
    private String topics;


    @Override
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void setTopics(String topics) {
        this.topics = topics;
    }

    @Autowired
    private KafkaProperties kafkaProperties;



    @Override
    public void run() {
        Map<String, Object> consumerProps = kafkaProperties.getConsumerProps();
        KafkaConsumer<String, T> consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(Arrays.asList(topics));
        boolean exitLoop = false;
        while (!exitLoop) {
            try {
                ConsumerRecords<String, T> records = consumer.poll(Duration.ofSeconds(5));
                for (ConsumerRecord<String, T> record : records) {
                    handler.handle(record.value());
                }
                consumer.commitSync();
            } catch (Exception ex) {
                LOGGER.error("Complete with error {}", ex.getMessage(), ex);
                handler.completeWithError(ex);
                consumer.commitSync();
//                exitLoop = true;
            }
        }
        consumer.close();
        handler.complete();
    }


}
