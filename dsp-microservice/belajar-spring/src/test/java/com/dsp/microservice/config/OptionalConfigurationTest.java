package com.dsp.microservice.config;

import com.dsp.microservice.model.Foo;
import com.dsp.microservice.model.FooBar;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OptionalConfigurationTest {

    @Test
    void testOptionalBean() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(OptionalConfiguration.class);

        Foo foo = context.getBean(Foo.class);
        FooBar fooBar = context.getBean(FooBar.class);

        assertNotNull(foo);
        assertSame(foo, fooBar.getFoo());
        assertNull(fooBar.getBar());
    }
}