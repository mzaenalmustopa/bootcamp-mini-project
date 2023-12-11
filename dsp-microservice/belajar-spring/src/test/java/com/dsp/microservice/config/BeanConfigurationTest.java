package com.dsp.microservice.config;

import com.dsp.microservice.model.Foo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeanConfigurationTest {
    private ApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(BeanConfiguration.class);
    }

    @Test
    void testBean() {
        Foo foo1 = context.getBean(Foo.class);
        Foo foo2 = context.getBean(Foo.class);
        Foo foo3 = new Foo();

        assertNotNull(foo1);
        assertNotNull(foo2);

        assertSame(foo1, foo2);

        assertNotNull(foo3);
        assertNotSame(foo1, foo3);
        assertNotSame(foo2, foo3);
    }
}