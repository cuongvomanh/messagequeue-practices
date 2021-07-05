package com.example.messagequeueinspring.runner;

import com.example.messagequeueinspring.config.Constants;
import com.example.messagequeueinspring.config.KafkaProperties;
import com.example.messagequeueinspring.domain.Book;
import com.example.messagequeueinspring.dto.BookDTO;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BookProduceRunner implements CommandLineRunner {
    private Logger LOGGER = LoggerFactory.getLogger(BookProduceRunner.class);
    private final KafkaProperties kafkaProperties;

    public BookProduceRunner(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Override
    public void run(String... args) throws Exception {
        BookDTO book = new BookDTO(1, "Harry Potter", 0);
        KafkaProducer producer = new KafkaProducer<>(kafkaProperties.getProducerProps());
//        while(true){
            Thread.sleep(5000L);
            producer.send(new ProducerRecord(Constants.BOOK_SAVE_TOPIC, book), printSendResultCallback());
//        }
    }

    @NotNull
    private Callback printSendResultCallback() {
        return (res, ex) -> {
            if (ex != null) {
                LOGGER.error("Send fail!");
            } else {
                LOGGER.info("Send success!");
            }
        };
    }
}
