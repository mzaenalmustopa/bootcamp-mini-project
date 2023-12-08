package com.bootcamp.pos.service;

import com.bootcamp.pos.model.request.AddressRequest;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<AddressRequest> getAll();
    Optional<AddressRequest> getById(String id);
    Optional<AddressRequest> save (AddressRequest request);
    Optional<AddressRequest> update (AddressRequest request, String id);
    Optional<AddressRequest> delete (String id);
}
