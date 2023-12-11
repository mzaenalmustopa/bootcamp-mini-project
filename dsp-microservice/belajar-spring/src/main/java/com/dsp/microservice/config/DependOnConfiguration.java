package com.dsp.microservice.config;

import com.dsp.microservice.model.Bar;
import com.dsp.microservice.model.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

@Slf4j
@Configuration
public class DependOnConfiguration {
    @Lazy
    @Bean(value = "fooDependOn")
    @DependsOn(value = {"barDependOn"})
    public Foo foo(){
        log.info("Create new Foo object");
        return new Foo();
    }
    @Bean(value = "barDependOn")
    public Bar bar(){
        log.info("Create new Bar object");
        return new Bar();
    }
}
