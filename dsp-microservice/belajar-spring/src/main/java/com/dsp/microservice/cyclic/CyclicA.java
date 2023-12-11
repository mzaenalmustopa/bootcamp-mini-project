package com.dsp.microservice.cyclic;

import lombok.Data;

@Data
public class CyclicA {
    private CyclicB cyclicB;

    public CyclicA(CyclicB cyclicB) {
        this.cyclicB = cyclicB;
    }
}
