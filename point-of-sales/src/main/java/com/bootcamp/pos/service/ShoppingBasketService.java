package com.bootcamp.pos.service;

import com.bootcamp.pos.model.request.ShoppingBasketRequest;

import java.util.List;
import java.util.Optional;

public interface ShoppingBasketService {
    List<ShoppingBasketRequest> getAll();
    Optional<ShoppingBasketRequest> getById(String id);
    Optional<ShoppingBasketRequest> save (ShoppingBasketRequest request);
    Optional<ShoppingBasketRequest> update (ShoppingBasketRequest request, String id);
    Optional<ShoppingBasketRequest> delete (String id);
}
