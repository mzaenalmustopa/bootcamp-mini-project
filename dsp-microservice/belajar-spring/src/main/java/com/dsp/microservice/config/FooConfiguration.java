package com.dsp.microservice.config;

import com.dsp.microservice.model.Foo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FooConfiguration {
    @Bean(value = "fooConfig")
    public Foo foo(){
        return new Foo();
    }
}
