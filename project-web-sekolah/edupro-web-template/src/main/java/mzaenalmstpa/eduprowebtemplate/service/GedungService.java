package mzaenalmstpa.eduprowebtemplate.service;

import mzaenalmstpa.eduprowebtemplate.model.request.GedungRequest;
import mzaenalmstpa.eduprowebtemplate.model.response.GedungResponse;

import java.util.List;
import java.util.Optional;

public interface GedungService {

    List<GedungResponse> getAll();
    Optional<GedungResponse> getById(String kode);
    Optional<GedungResponse> save (GedungRequest request);
    Optional<GedungResponse> update (GedungRequest request);
    Optional<GedungResponse> delete(GedungRequest request);
}
