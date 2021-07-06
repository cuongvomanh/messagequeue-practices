package com.example.messagequeueinspring.scheduler;

import com.example.messagequeueinspring.processor.ConsumeProcessorTemplate;
import com.example.messagequeueinspring.processor.Processor;

public interface Scheduler {
    public void schedule(Processor processor);
}
