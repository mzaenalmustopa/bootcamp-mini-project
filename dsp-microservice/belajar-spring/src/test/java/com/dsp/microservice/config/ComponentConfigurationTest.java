package com.dsp.microservice.config;

import com.dsp.microservice.repository.CategoryRepository;
import com.dsp.microservice.repository.CustomerRepository;
import com.dsp.microservice.repository.ProductRepository;
import com.dsp.microservice.service.CategoryService;
import com.dsp.microservice.service.CustomerService;
import com.dsp.microservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ComponentConfigurationTest {
    private ApplicationContext context;
    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(ComponentConfiguration.class);
    }

    @Test
    void testProductService() {
        ProductService service = context.getBean("productService", ProductService.class);
        assertNotNull(service);
    }

    @Test
    void testConstructorInject() {
        ProductService service = context.getBean(ProductService.class);
        ProductRepository repository = context.getBean(ProductRepository.class);

        assertNotNull(service);
        assertNotNull(repository);

        assertSame(repository, service.getRepository());
    }

    @Test
    void testSetterInjection() {

        CategoryService service = context.getBean(CategoryService.class);
        CategoryRepository repository = context.getBean(CategoryRepository.class);
        CategoryRepository repository2 = context.getBean("categoryRepository", CategoryRepository.class);

        assertNotNull(service);
        assertNotNull(repository);

        assertSame(repository, service.getRepository());
        assertSame(repository, repository2);
        assertSame(repository2, service.getRepository());
    }

    @Test
    void testFieldInjection() {
        CustomerService service = context.getBean(CustomerService.class);
        CustomerRepository repository = context.getBean(CustomerRepository.class);

        assertSame(service.getRepository(), repository);
    }
}