package com.bootcamp.service;

import com.bootcamp.model.RuangModel;

import java.util.List;
import java.util.Optional;

public interface RuangService {

    List<RuangModel> getAll();
    RuangModel getById(String id);
    Optional<RuangModel> save(RuangModel request);
    Optional<RuangModel> update(String id, RuangModel request);
    Optional<RuangModel> delete(String id);
}
