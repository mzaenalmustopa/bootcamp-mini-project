package com.dsp.microservice.config;

import com.dsp.microservice.component.MultiFoo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MultiOptionalConfigurationTest {

    @Test
    void testMultiFoo() {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                MultiOptionalConfiguration.class
        );

        MultiFoo multiFoo = context.getBean(MultiFoo.class);
        assertEquals(3, multiFoo.getFoos().size());
    }
}