package com.example.messagequeueinspring.service.handler;

import com.example.messagequeueinspring.dto.BookDTO;
import com.example.messagequeueinspring.exception.BadRequestException;
import com.example.messagequeueinspring.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BookHandler implements Handler<BookDTO> {
    private Logger LOGGER = LoggerFactory.getLogger(BookHandler.class);
    private final BookService bookService;

    public BookHandler(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void handle(BookDTO book) throws BadRequestException {
        LOGGER.info("Book handling! " + book);
        bookService.save(book);
    }

    @Override
    public void completeWithError(Exception ex) {

    }

    @Override
    public void complete() {

    }
}
