package com.dsp.microservice.config;

import com.dsp.microservice.model.Foo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScopeConfigurationTest {
    @Test
    void testScope() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfiguration.class);

        Foo foo1 = context.getBean("fooScope", Foo.class);
        Foo foo2 = context.getBean("fooScope", Foo.class);
        Foo foo3 = context.getBean("fooScope", Foo.class);

        assertNotSame(foo1, foo2);
        assertNotSame(foo1, foo3);
        assertNotSame(foo2, foo3);
    }
}