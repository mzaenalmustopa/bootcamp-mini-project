package com.bootcamp.service;


import com.bootcamp.model.JurusanModel;

import java.util.List;
import java.util.Optional;

public interface JurusanService {

    List<JurusanModel> getAll();
    JurusanModel getById (String id);
    Optional<JurusanModel> save(JurusanModel request);
    Optional<JurusanModel> update(String id, JurusanModel request);
    Optional<JurusanModel> delete(String id);
}
