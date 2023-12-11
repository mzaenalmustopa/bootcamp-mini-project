package com.dsp.microservice.config;

import com.dsp.microservice.model.Bar;
import com.dsp.microservice.model.Foo;
import com.dsp.microservice.model.FooBar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DependencyInjectionConfigurationTest {
    private ApplicationContext context;
    @BeforeEach
    void setUp() {
        context =
                new AnnotationConfigApplicationContext(DependencyInjectionConfiguration.class);
    }

    @Test
    void testDI() {
        Foo foo = context.getBean("fooFirst", Foo.class);
        Bar bar = context.getBean(Bar.class);
        FooBar fooBar = context.getBean(FooBar.class);

        assertNotNull(foo);
        assertNotNull(bar);
        assertNotNull(fooBar);

        assertSame(fooBar.getFoo(), foo);
        assertSame(fooBar.getBar(), bar);
    }
}