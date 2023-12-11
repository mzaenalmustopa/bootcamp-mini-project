package com.dsp.microservice.factoryBean;

import com.dsp.microservice.model.PaymentGatewayClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component(value = "paymentGatewayClient")
public class PaymentGatewayClientFactoryBean implements FactoryBean<PaymentGatewayClient> {
    @Override
    public PaymentGatewayClient getObject() throws Exception {
        PaymentGatewayClient client = new PaymentGatewayClient();
        client.setEndpoint("https://payment.com");
        client.setPublicKey("PublicKeySecret32!");
        client.setPrivateKey("PrivateKeySecret32!");

        return client;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
