package com.example.messagequeueinspring.dto.converter;

import com.example.messagequeueinspring.domain.Book;
import com.example.messagequeueinspring.dto.BookDTO;
import com.example.messagequeueinspring.exception.BadRequestException;
import com.example.messagequeueinspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {
    private final BookRepository bookRepository;

    public BookConverter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book toEntity(BookDTO bookDTO) throws BadRequestException {
        Book book = bookRepository.findById(bookDTO.getId()).orElse(new Book());
        book.setName(bookDTO.getName());
        book.setStatus(bookDTO.getStatus());
        return book;
    }
}
