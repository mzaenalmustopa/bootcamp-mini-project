package com.dsp.microservice.cyclic;

import lombok.Data;

@Data
public class CyclicC {
    private CyclicA cyclicA;

    public CyclicC(CyclicA cyclicA) {
        this.cyclicA = cyclicA;
    }
}
