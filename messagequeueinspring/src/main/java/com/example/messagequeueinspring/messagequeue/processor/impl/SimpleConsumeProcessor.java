package com.example.messagequeueinspring.messagequeue.processor.impl;

import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumer;
import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumerRecord;
import com.example.messagequeueinspring.messagequeue.comsumer.MessageConsumerRecords;
import com.example.messagequeueinspring.messagequeue.processor.ConsumeProcessorTemplate;
import com.example.messagequeueinspring.service.handler.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Scope("prototype")
public class SimpleConsumeProcessor<K, V> implements ConsumeProcessorTemplate {
    private final Logger LOGGER = LoggerFactory.getLogger(SimpleConsumeProcessor.class);
    private Handler<V> handler;
    private String topics;
    @Autowired
    private ApplicationContext context;

    @Autowired
    private MessageConsumer consumer;

    @Override
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void setTopics(String topics) {
        this.topics = topics;
    }


    @Override
    public void run() {
        consumer.subscribe(topics);
        boolean exitLoop = false;
        while (!exitLoop) {
            try {
                MessageConsumerRecords<String, V> records = consumer.poll(Duration.ofSeconds(5));
                for (MessageConsumerRecord<String, V> record : records) {
                    handler.handle(record.value());
                }
                consumer.commitSync();
            } catch (Exception ex) {
                LOGGER.error("Complete with error {}", ex.getMessage(), ex);
                handler.completeWithError(ex);
                consumer.commitSync();
                consumer.subscribe(topics);
//                exitLoop = true;
            }
        }
        consumer.close();
        handler.complete();
    }


}
