package com.example.messagequeueinspring.processor;

import com.example.messagequeueinspring.config.KafkaProperties;
import com.example.messagequeueinspring.handler.Handler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collection;
import java.util.Map;

public class ConsumeStrategyTemplate<T> implements Runnable {
    private final Logger LOGGER = LoggerFactory.getLogger(ConsumeStrategyTemplate.class);
    private Handler<T> handler;
    private Collection<String> topics;
    private KafkaProperties kafkaProperties;


    public ConsumeStrategyTemplate(Handler<T> handler, Collection<String> topics, KafkaProperties kafkaProperties) {
        this.handler = handler;
        this.topics = topics;
        this.kafkaProperties = kafkaProperties;

    }

    @Override
    public void run() {
        Map<String, Object> consumerProps = kafkaProperties.getConsumerProps();
        KafkaConsumer<String, T> consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(topics);
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
