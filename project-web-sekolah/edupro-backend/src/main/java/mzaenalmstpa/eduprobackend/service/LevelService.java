package mzaenalmstpa.eduprobackend.service;

import mzaenalmstpa.eduprobackend.model.request.LevelRequest;
import mzaenalmstpa.eduprobackend.model.response.LevelResponse;

import java.util.List;
import java.util.Optional;

public interface LevelService {
    List<LevelResponse> getAll();
    Optional<LevelResponse> getById(Integer id, String kode);
    Optional<LevelResponse> save (LevelRequest request);
    Optional<LevelResponse> update(LevelRequest request, Integer id, String kode);
    Optional<LevelResponse> delete(Integer id, String kode);
}
