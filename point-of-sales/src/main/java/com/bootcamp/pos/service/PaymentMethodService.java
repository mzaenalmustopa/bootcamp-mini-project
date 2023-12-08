package com.bootcamp.pos.service;

import com.bootcamp.pos.model.request.PaymentMethodRequest;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodService {
    List<PaymentMethodRequest> getAll();
    Optional<PaymentMethodRequest> getById(String id);
    Optional<PaymentMethodRequest> save (PaymentMethodRequest request);
    Optional<PaymentMethodRequest> update (PaymentMethodRequest request, String id);
    Optional<PaymentMethodRequest> delete (String id);
}
