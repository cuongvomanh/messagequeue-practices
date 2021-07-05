package com.example.messagequeueinspring.runner;

import com.example.messagequeueinspring.processor.ConsumeStrategyTemplate;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("threadperconsumer")
public class ThreadPerConsumerScheduler implements Scheduler{
    @Override
    public void schedule(ConsumeStrategyTemplate consumeStrategy) {
        Thread thread = new Thread(consumeStrategy);
        thread.start();
    }
}
