package com.dsp.microservice.config;

import com.dsp.microservice.model.Foo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DuplicateBeanConfigurationTest {
    private ApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        applicationContext =
                new AnnotationConfigApplicationContext(DuplicateBeanConfiguration.class);
    }

    @Test
    void testDuplicate() {
        Foo foo1 = applicationContext.getBean("foo1", Foo.class);
        Foo foo2 = applicationContext.getBean("foo2", Foo.class);

        assertNotSame(foo1, foo2);
        //assertSame(foo1, foo2);
    }

    @Test
    void testPrimary() {
        Foo foo = applicationContext.getBean(Foo.class);
        Foo foo1 = applicationContext.getBean("foo1", Foo.class);
        Foo foo2 = applicationContext.getBean("foo2", Foo.class);

        assertSame(foo1, foo);
        assertNotSame(foo2, foo);
    }
}