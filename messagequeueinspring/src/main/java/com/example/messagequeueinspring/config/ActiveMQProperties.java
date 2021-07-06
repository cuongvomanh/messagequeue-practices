package com.example.messagequeueinspring.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "activemq")
@ConditionalOnMissingBean(type = "MessageQueueProperties.class")
public class ActiveMQProperties implements MessageQueueProperties {
    private String url = "tcp://localhost:61616";
    private String queueName = "testqueue";
    private String userName = "admin";
    private String password = "admin";

    public ActiveMQProperties() {
    }

    public ActiveMQProperties(String url, String queueName, String userName, String password) {
        this.url = url;
        this.queueName = queueName;
        this.userName = userName;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
