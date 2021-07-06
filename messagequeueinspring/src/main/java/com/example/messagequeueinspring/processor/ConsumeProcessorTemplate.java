package com.example.messagequeueinspring.processor;

import com.example.messagequeueinspring.config.KafkaProperties;
import com.example.messagequeueinspring.handler.Handler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collection;
import java.util.Map;

public interface ConsumeProcessorTemplate<T> extends Processor {

}
