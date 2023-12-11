package com.dsp.microservice.cyclic;

import lombok.Data;

@Data
public class CyclicB {
    private CyclicC cyclicC;

    public CyclicB(CyclicC cyclicC) {
        this.cyclicC = cyclicC;
    }
}
