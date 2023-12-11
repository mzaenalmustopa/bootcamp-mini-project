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
class MainConfigTest {
    private ApplicationContext context;
    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(MainConfig.class);
    }

    @Test
    void testLoadConfig() {
        Foo foo= context.getBean("fooConfig", Foo.class);
        assertNotNull(foo);

        Bar bar = context.getBean("barConfig", Bar.class);
        assertNotNull(bar);
    }
}