package com.bootcamp.pos.service;

import com.bootcamp.pos.model.request.BasketItemsRequest;

import java.util.List;
import java.util.Optional;

public interface BasketItemService {
    List<BasketItemsRequest> getAll();
    Optional<BasketItemsRequest> getById(String id);
    Optional<BasketItemsRequest> save (BasketItemsRequest request);
    Optional<BasketItemsRequest> update (BasketItemsRequest request, String id);
    Optional<BasketItemsRequest> delete (String id);
}
