package com.dsp.microservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloWorldConfigTest {
    @Test
    void testConfig() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(HelloWorldConfig.class);

        assertNotNull(context);
    }

    @Test
    void testSum() {
        int a = 10;
        int b = 11;
        int c = a + b;
        assertEquals(21, c);
        assertNotEquals(15, c);
    }
}