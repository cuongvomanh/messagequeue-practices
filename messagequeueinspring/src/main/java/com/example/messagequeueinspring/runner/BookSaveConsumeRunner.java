package com.example.messagequeueinspring.runner;

import com.example.messagequeueinspring.config.Constants;
import com.example.messagequeueinspring.exception.BadRequestException;
import com.example.messagequeueinspring.service.handler.BookHandler;
import com.example.messagequeueinspring.messagequeue.processor.ConsumeProcessorTemplate;
import com.example.messagequeueinspring.scheduler.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BookSaveConsumeRunner {
    private final Scheduler scheduler;
    private final BookHandler bookHandler;
    @Autowired
    private ConsumeProcessorTemplate consumeProcessor;

    public BookSaveConsumeRunner(Scheduler scheduler, BookHandler bookHandler) {
        this.scheduler = scheduler;
        this.bookHandler = bookHandler;
    }

    @PostConstruct
    public void run() throws BadRequestException {
        consumeProcessor.setTopics(Constants.BOOK_SAVE_TOPIC);
        consumeProcessor.setHandler(bookHandler);
        scheduler.schedule(consumeProcessor);
    }
}
