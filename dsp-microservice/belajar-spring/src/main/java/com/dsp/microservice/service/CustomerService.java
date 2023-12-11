package com.dsp.microservice.service;

import com.dsp.microservice.repository.CustomerRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {
    @Getter
    @Autowired
    @Qualifier("normalCustomerRepository")
    private CustomerRepository repository;

    @Autowired
    @Qualifier("premiumCustomerRepository")
    private CustomerRepository repository2;
}
