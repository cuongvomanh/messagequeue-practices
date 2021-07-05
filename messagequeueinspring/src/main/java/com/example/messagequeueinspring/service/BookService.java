package com.example.messagequeueinspring.service;

import com.example.messagequeueinspring.domain.Book;
import com.example.messagequeueinspring.dto.BookDTO;
import com.example.messagequeueinspring.dto.converter.BookConverter;
import com.example.messagequeueinspring.exception.BadRequestException;
import com.example.messagequeueinspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookConverter bookConverter;
    public void save(BookDTO book) throws BadRequestException {
        bookConverter.toEntity(book);
//        bookRepository.save(bookConverter.toEntity(book));
    }
}
