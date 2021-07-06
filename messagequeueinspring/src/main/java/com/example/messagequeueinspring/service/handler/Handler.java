package com.example.messagequeueinspring.service.handler;

import com.example.messagequeueinspring.exception.BadRequestException;

public interface Handler<T> {
    public void handle(T t) throws BadRequestException;
    public void completeWithError(Exception ex);
    public void complete();
}
