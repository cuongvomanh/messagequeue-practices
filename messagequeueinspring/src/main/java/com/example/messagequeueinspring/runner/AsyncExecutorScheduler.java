package com.example.messagequeueinspring.runner;

import com.example.messagequeueinspring.config.AsyncConfiguration;
import com.example.messagequeueinspring.processor.ConsumeStrategyTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(value = Scheduler.class)
public class AsyncExecutorScheduler implements Scheduler {
    private final AsyncConfiguration asyncConfiguration;

    public AsyncExecutorScheduler(AsyncConfiguration asyncConfiguration) {
        this.asyncConfiguration = asyncConfiguration;
    }

    public void schedule(ConsumeStrategyTemplate consumeStrategy) {
        asyncConfiguration.getAsyncExecutor().execute(consumeStrategy);
    }
}
