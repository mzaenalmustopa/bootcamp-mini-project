package com.bootcamp.pos.service;

import com.bootcamp.pos.model.request.InventoryLocRequest;

import java.util.List;
import java.util.Optional;

public interface InventoryLocService {
    List<InventoryLocRequest> getAll();
    Optional<InventoryLocRequest> getById(String id);
    Optional<InventoryLocRequest> save (InventoryLocRequest request);
    Optional<InventoryLocRequest> update (InventoryLocRequest request, String id);
    Optional<InventoryLocRequest> delete (String id);
}
