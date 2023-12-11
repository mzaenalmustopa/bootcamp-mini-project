package com.dsp.microservice.model;

import lombok.Data;

@Data
public class FooBar {
    private Foo foo;
    private Bar bar;

    public FooBar(Foo foo, Bar bar) {
        this.foo = foo;
        this.bar = bar;
    }
}
