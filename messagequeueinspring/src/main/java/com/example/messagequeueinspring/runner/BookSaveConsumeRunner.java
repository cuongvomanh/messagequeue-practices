package com.example.messagequeueinspring.runner;

import com.example.messagequeueinspring.config.KafkaProperties;
import com.example.messagequeueinspring.config.Constants;
import com.example.messagequeueinspring.domain.Book;
import com.example.messagequeueinspring.dto.BookDTO;
import com.example.messagequeueinspring.exception.BadRequestException;
import com.example.messagequeueinspring.handler.BookHandler;
import com.example.messagequeueinspring.processor.ConsumeStrategyTemplate;
import org.apache.kafka.clients.producer.Callback;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class BookSaveConsumeRunner {
    private final Scheduler scheduler;
    private final KafkaProperties kafkaProperties;
    private final BookHandler bookHandler;

    public BookSaveConsumeRunner(Scheduler scheduler, KafkaProperties kafkaProperties, BookHandler bookHandler) {
        this.scheduler = scheduler;
        this.kafkaProperties = kafkaProperties;
        this.bookHandler = bookHandler;
    }

    @PostConstruct
    public void run() throws BadRequestException {
        ConsumeStrategyTemplate<BookDTO> bookConsumeStrategy = new ConsumeStrategyTemplate<>(bookHandler, Arrays.asList(Constants.BOOK_SAVE_TOPIC), kafkaProperties);
        scheduler.schedule(bookConsumeStrategy);
    }
}
