package com.dsp.microservice.config;

import com.dsp.microservice.model.Foo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ScopeConfiguration {
    @Bean(value = "fooScope")
    @Scope("prototype")
    public Foo foo(){
        return new Foo();
    }
}
