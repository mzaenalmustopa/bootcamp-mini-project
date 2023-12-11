package com.dsp.microservice.config;

import com.dsp.microservice.model.Bar;
import com.dsp.microservice.model.Foo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DependOnConfigurationTest {
    private ApplicationContext context;
    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(DependOnConfiguration.class);
    }

    @Test
    void testDependOn() {
        Foo foo = context.getBean(Foo.class);
        Bar bar = context.getBean(Bar.class);

        assertNotNull(foo);
        assertNotNull(bar);
    }
}