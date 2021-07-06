package com.example.messagequeueinspring.messagequeue.processor;

public interface ProduceProcessorTemplate extends Processor{
    void setTopics(String topics);
}
