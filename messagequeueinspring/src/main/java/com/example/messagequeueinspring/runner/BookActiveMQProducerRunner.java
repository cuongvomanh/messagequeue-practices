package com.example.messagequeueinspring.runner;

import com.example.messagequeueinspring.processor.ActiveMQProducerProcessor;
import com.example.messagequeueinspring.processor.Processor;
import com.example.messagequeueinspring.scheduler.Scheduler;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.*;

@Component
@Profile("activemq & messagequeuetest")
public class BookActiveMQProducerRunner {
    @Autowired
    private Scheduler scheduler;
    @PostConstruct
    public void run(){
        Processor processor = new ActiveMQProducerProcessor();
        scheduler.schedule(processor);
    }
}
