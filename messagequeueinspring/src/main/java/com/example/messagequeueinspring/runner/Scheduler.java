package com.example.messagequeueinspring.runner;

import com.example.messagequeueinspring.processor.ConsumeStrategyTemplate;

public interface Scheduler {
    public void schedule(ConsumeStrategyTemplate consumeStrategy);
}
