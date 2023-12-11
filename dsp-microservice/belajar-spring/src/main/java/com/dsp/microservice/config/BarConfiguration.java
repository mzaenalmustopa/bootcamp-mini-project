package com.dsp.microservice.config;

import com.dsp.microservice.model.Bar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BarConfiguration {
    @Bean(value = "barConfig")
    public Bar bar(){
        return new Bar();
    }
}
