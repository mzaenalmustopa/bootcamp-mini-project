package com.dsp.microservice.config;

import com.dsp.microservice.model.Foo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public Foo foo(){
        return new Foo();
    }
}
