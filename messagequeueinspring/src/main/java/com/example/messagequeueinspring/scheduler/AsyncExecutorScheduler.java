package com.example.messagequeueinspring.scheduler;

import com.example.messagequeueinspring.config.AsyncConfiguration;
import com.example.messagequeueinspring.processor.ConsumeProcessorTemplate;
import com.example.messagequeueinspring.processor.Processor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(value = Scheduler.class)
public class AsyncExecutorScheduler implements Scheduler {
    private final AsyncConfiguration asyncConfiguration;

    public AsyncExecutorScheduler(AsyncConfiguration asyncConfiguration) {
        this.asyncConfiguration = asyncConfiguration;
    }

    public void schedule(Processor processor) {
        asyncConfiguration.getAsyncExecutor().execute(processor);
    }
}
