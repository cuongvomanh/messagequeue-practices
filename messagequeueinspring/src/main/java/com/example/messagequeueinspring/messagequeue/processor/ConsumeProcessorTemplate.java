package com.example.messagequeueinspring.messagequeue.processor;

import com.example.messagequeueinspring.service.handler.Handler;

public interface ConsumeProcessorTemplate<T> extends Processor {
    public void setTopics(String topics);
    public void setHandler(Handler handler);
}
