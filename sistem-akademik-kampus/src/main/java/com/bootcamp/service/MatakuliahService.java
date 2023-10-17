package com.bootcamp.service;

import com.bootcamp.model.MatakuliahModel;


import java.util.List;
import java.util.Optional;

public interface MatakuliahService {

    List<MatakuliahModel> getAll();
    MatakuliahModel getById(String id);
    Optional<MatakuliahModel> save(MatakuliahModel request);
    Optional<MatakuliahModel> update(String id, MatakuliahModel request);
    Optional<MatakuliahModel> delete(String id);
}
