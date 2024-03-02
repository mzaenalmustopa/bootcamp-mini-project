package mzaenalmstpa.eduprobackend.service;

import mzaenalmstpa.eduprobackend.model.request.RuanganRequest;
import mzaenalmstpa.eduprobackend.model.response.RuanganResponse;

import java.util.List;
import java.util.Optional;

public interface RuanganService {
    List<RuanganResponse> getAll();
    Optional<RuanganResponse> getById(String kode);
    Optional<RuanganResponse> save (RuanganRequest request);
    Optional<RuanganResponse> update(RuanganRequest request, String kode);
    Optional<RuanganResponse> delete(String kode);
}
