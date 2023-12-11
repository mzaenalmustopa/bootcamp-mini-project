package com.dsp.microservice.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LifeCycleConfigTest {
    private ConfigurableApplicationContext context;
    @BeforeEach
    void setUp() {
        context =
                new AnnotationConfigApplicationContext(LifeCycleConfig.class);
    }

    @Test
    void testLifeCycle() {
        Connection connection = context.getBean(Connection.class);
        assertNotNull(connection);

        context.close();
    }

    @Test
    void testLifeCycleMethod() {
        Server server = context.getBean(Server.class);
        assertNotNull(server);

        context.close();
    }
}