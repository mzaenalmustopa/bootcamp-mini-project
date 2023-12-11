package com.dsp.microservice.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class CyclicConfigurationTest {

    @Test
    void testCyclic() {
        try {
            ApplicationContext context = new AnnotationConfigApplicationContext(CyclicConfiguration.class);
            assertNull(context);
            Assertions.fail("It mus be fail because cyclic");
        }catch (BeansException e){
            e.printStackTrace();
        }
    }
}