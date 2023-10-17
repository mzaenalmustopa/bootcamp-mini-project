package com.bootcamp.service;

import com.bootcamp.model.FakultasModel;

import java.util.List;
import java.util.Optional;

public interface FakultasService {
    List<FakultasModel> getAll();
    FakultasModel getById(String id);
    Optional<FakultasModel> save(FakultasModel request);
    Optional<FakultasModel> update(String id, FakultasModel request);
    Optional <FakultasModel> delete(String id);

}
