package com.example.messagequeueinspring.runner;

import com.example.messagequeueinspring.config.Constants;
import com.example.messagequeueinspring.messagequeue.processor.ProduceProcessorTemplate;
import com.example.messagequeueinspring.scheduler.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("messagequeuetest")
public class BookProduceRunner  {
    private Logger LOGGER = LoggerFactory.getLogger(BookProduceRunner.class);
    @Autowired
    private ProduceProcessorTemplate produceProcessor;
    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void run() throws Exception {
        produceProcessor.setTopics(Constants.BOOK_SAVE_TOPIC);
        scheduler.schedule(produceProcessor);
    }
}
