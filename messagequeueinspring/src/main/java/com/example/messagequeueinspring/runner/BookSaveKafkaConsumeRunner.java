package com.example.messagequeueinspring.runner;

import com.example.messagequeueinspring.config.KafkaProperties;
import com.example.messagequeueinspring.config.Constants;
import com.example.messagequeueinspring.dto.BookDTO;
import com.example.messagequeueinspring.exception.BadRequestException;
import com.example.messagequeueinspring.handler.BookHandler;
import com.example.messagequeueinspring.processor.ConsumeProcessorTemplate;
import com.example.messagequeueinspring.processor.KafkaConsumeProcessor;
import com.example.messagequeueinspring.scheduler.Scheduler;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@Profile("kafka")
public class BookSaveKafkaConsumeRunner {
    private final Scheduler scheduler;
    private final KafkaProperties kafkaProperties;
    private final BookHandler bookHandler;

    public BookSaveKafkaConsumeRunner(Scheduler scheduler, KafkaProperties kafkaProperties, BookHandler bookHandler) {
        this.scheduler = scheduler;
        this.kafkaProperties = kafkaProperties;
        this.bookHandler = bookHandler;
    }

    @PostConstruct
    public void run() throws BadRequestException {
        ConsumeProcessorTemplate<BookDTO> bookConsumeStrategy = new KafkaConsumeProcessor<>(bookHandler, Arrays.asList(Constants.BOOK_SAVE_TOPIC), kafkaProperties);
        scheduler.schedule(bookConsumeStrategy);
    }
}
