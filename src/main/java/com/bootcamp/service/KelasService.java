package com.bootcamp.service;

import com.bootcamp.model.KelasModel;

import java.util.List;
import java.util.Optional;

public interface KelasService {
    List<KelasModel> getAll();
    KelasModel getById(String id);
    Optional<KelasModel> save(KelasModel request);
    Optional<KelasModel> update(String id, KelasModel request);
    Optional<KelasModel> delete(String id);

}
