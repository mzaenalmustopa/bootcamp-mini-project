package com.bootcamp.service;

import com.bootcamp.model.MahasiswaModel;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MahasiswaService {
    List<MahasiswaModel> getAll();
    MahasiswaModel getById(String id);
    Optional<MahasiswaModel> save(MahasiswaModel request);
    Optional<MahasiswaModel> update(String id, MahasiswaModel request);
    Optional<MahasiswaModel> delete(String id);
}
