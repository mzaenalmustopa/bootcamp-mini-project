package com.dsp.microservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
        FooConfiguration.class,
        BarConfiguration.class
})
//@ComponentScan(basePackages = {"com.dsp.microservice.config"})
public class MainConfig {

}
