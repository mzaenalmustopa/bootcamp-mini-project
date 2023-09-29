package com.bootcamp.service;

import com.bootcamp.model.GedungModel;


import java.util.List;
import java.util.Optional;

public interface GedungService {

    List<GedungModel> getAll();
    GedungModel getById (String id);
    Optional<GedungModel> save(GedungModel request);
    Optional<GedungModel> update (String id, GedungModel request);
    Optional<GedungModel> delete(String id);
}
