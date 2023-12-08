package com.bootcamp.pos.service;

import com.bootcamp.pos.model.request.AddressTypeRequest;

import java.util.List;
import java.util.Optional;

public interface AddressTypeService {
    List<AddressTypeRequest> getAll();

    public Boolean validAddressCode(AddressTypeRequest request);
    public Boolean validAddressDesc(AddressTypeRequest request);

    Optional<AddressTypeRequest> getById(String id);
    Optional<AddressTypeRequest> save (AddressTypeRequest request);
    Optional<AddressTypeRequest> update (AddressTypeRequest request, String id);
    Optional<AddressTypeRequest> delete (String id);
}
