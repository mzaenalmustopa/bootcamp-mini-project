package mzaenalmstpa.eduprobackend.service;

import mzaenalmstpa.eduprobackend.model.request.GedungRequest;
import mzaenalmstpa.eduprobackend.model.response.GedungResponse;

import java.util.List;
import java.util.Optional;

public interface GedungService {
    List<GedungResponse> getAll();
    Optional<GedungResponse> geyById(String kode);
    Optional<GedungResponse> save (GedungRequest request);
    Optional<GedungResponse> update(GedungRequest request, String kode);
    Optional<GedungResponse> delete(String kode);
}
