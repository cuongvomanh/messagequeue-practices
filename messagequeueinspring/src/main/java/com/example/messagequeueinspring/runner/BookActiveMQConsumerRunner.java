//package com.example.messagequeueinspring.runner;
//
//import com.example.messagequeueinspring.config.ActiveMQProperties;
//import com.example.messagequeueinspring.messagequeue.processor.ActiveMQConsumeProcessor;
//import com.example.messagequeueinspring.messagequeue.processor.ConsumeProcessorTemplate;
//import com.example.messagequeueinspring.scheduler.Scheduler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//@Component
//@Profile("activemq")
//public class BookActiveMQConsumerRunner {
//
//    @Autowired
//    private Scheduler scheduler;
//    @Autowired
//    private ActiveMQProperties activeMQProperties;
//    @PostConstruct
//    public void run() {
//        ConsumeProcessorTemplate<Object> consumeStrategy = new ActiveMQConsumeProcessor(activeMQProperties);
//        scheduler.schedule(consumeStrategy);
//    }
//}
