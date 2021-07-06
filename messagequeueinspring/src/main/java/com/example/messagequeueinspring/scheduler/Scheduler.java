package com.example.messagequeueinspring.scheduler;

import com.example.messagequeueinspring.messagequeue.processor.Processor;

public interface Scheduler {
    public void schedule(Processor processor);
}
