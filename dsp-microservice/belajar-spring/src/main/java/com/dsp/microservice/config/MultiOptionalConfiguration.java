package com.dsp.microservice.config;

import com.dsp.microservice.component.MultiFoo;
import com.dsp.microservice.model.Foo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Configuration
@Import(value = MultiFoo.class)
public class MultiOptionalConfiguration {

    @Bean(value = "multiFooFirst")
    @Primary
    public Foo foo1(){
        return new Foo();
    }

    @Bean(value = "multiFooSecond")
    public Foo foo2(){
        return new Foo();
    }

    @Bean(value = "multiFooThird")
    public Foo foo3(){
        return new Foo();
    }
}
