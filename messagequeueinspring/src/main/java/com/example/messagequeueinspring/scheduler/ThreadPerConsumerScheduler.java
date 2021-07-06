package com.example.messagequeueinspring.scheduler;

import com.example.messagequeueinspring.processor.ConsumeProcessorTemplate;
import com.example.messagequeueinspring.processor.Processor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("threadperprocessor")
public class ThreadPerConsumerScheduler implements Scheduler{
    @Override
    public void schedule(Processor processor) {
        Thread thread = new Thread(processor);
        thread.start();
    }
}
