package com.example.messagequeuejhipster;

import com.example.messagequeuejhipster.MessagequeuejhipsterApp;
import com.example.messagequeuejhipster.config.TestSecurityConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { MessagequeuejhipsterApp.class, TestSecurityConfiguration.class })
public @interface IntegrationTest {
}
