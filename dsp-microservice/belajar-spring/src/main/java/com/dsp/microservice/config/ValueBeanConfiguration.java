package com.dsp.microservice.config;

import com.dsp.microservice.model.Foo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ValueBeanConfiguration {

    @Primary
    @Bean(value = "fooFirstValue")
    public Foo foo1(){
        Foo foo = new Foo();
        return foo;
    }
    @Bean(value = "fooSecondValue")
    public Foo foo2(){
        Foo foo = new Foo();
        return foo;
    }
}
