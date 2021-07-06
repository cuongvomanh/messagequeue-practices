package com.example.messagequeueinspring.runner;

import com.example.messagequeueinspring.processor.ActiveMQConsumeProcessor;
import com.example.messagequeueinspring.processor.ConsumeProcessorTemplate;
import com.example.messagequeueinspring.scheduler.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("activemq")
public class BookActiveMQConsumerRunner {

    @Autowired
    private Scheduler scheduler;
    @PostConstruct
    public void run() {
        ConsumeProcessorTemplate<Object> consumeStrategy = new ActiveMQConsumeProcessor();
        scheduler.schedule(consumeStrategy);
    }
}
