package com.dsp.microservice.config;

import com.dsp.microservice.model.PaymentGatewayClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FactoryConfigurationTest {
    private ApplicationContext context;
    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(
                FactoryConfiguration.class
        );
    }

    @Test
    void testFactoryBean() {
        PaymentGatewayClient client = context.getBean("paymentGatewayClient",
                PaymentGatewayClient.class);

        assertEquals("https://payment.com", client.getEndpoint());
        assertEquals("PublicKeySecret32!", client.getPublicKey());
        assertEquals("PrivateKeySecret32!", client.getPrivateKey());
    }
}