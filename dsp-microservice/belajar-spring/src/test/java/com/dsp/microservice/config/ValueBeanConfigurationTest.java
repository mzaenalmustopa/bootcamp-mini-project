package com.dsp.microservice.config;

import com.dsp.microservice.model.Foo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValueBeanConfigurationTest {
    private ApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        applicationContext =
                new AnnotationConfigApplicationContext(ValueBeanConfiguration.class);
    }

    @Test
    void testValueBean() {
        Foo foo = applicationContext.getBean(Foo.class);
        Foo foo1 = applicationContext.getBean("fooFirstValue", Foo.class);
        Foo foo2 = applicationContext.getBean("fooSecondValue", Foo.class);

        assertSame(foo1, foo);
        assertNotSame(foo2, foo);
    }
}