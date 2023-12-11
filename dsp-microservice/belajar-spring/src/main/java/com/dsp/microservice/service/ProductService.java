package com.dsp.microservice.service;

import com.dsp.microservice.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Data
public class ProductService {
    private ProductRepository repository;
    private String name;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductService(ProductRepository repository, String name) {
        this.repository = repository;
        this.name = name;
    }

    @PostConstruct
    public void start(){
        log.info("Product service start");
    }

    @PreDestroy
    public void stop(){
        log.info("Product service stop");
    }
}
