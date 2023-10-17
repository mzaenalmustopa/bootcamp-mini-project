package com.bootcamp.service;

import com.bootcamp.model.DosenModel;

import java.util.List;
import java.util.Optional;

public interface DosenService {

    List<DosenModel> getAll();
    DosenModel getById(String id);
    Optional<DosenModel> save(DosenModel request);
    Optional<DosenModel> update(String id, DosenModel request);
    Optional<DosenModel> delete (String id);
}
