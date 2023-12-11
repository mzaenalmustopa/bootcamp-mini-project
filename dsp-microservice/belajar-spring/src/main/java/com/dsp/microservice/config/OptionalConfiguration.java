package com.dsp.microservice.config;

import com.dsp.microservice.model.Bar;
import com.dsp.microservice.model.Foo;
import com.dsp.microservice.model.FooBar;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class OptionalConfiguration {

    @Bean(value = "fooOption")
    public Foo foo(){
        return new Foo();
    }

    @Bean(value = "fooBarOption")
    public FooBar fooBar(@Qualifier("fooOption") Optional<Foo> foo,
                         Optional<Bar> bar){
        return new FooBar(
                foo.orElse(null),
                bar.orElse(null)
        );
    }
}
