//package com.example.messagequeueinspring.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//@Configuration
//public class MessageQueueConfiguration {
//    @Bean
//    @Profile("activemq")
//    MessageQueueProperties activeMQProperties(){
//        return new ActiveMQProperties();
//    }
//
//    @Bean
//    @Profile("kafka")
//    MessageQueueProperties kafkaProperties(){
//        return new KafkaProperties();
//    }
//}
