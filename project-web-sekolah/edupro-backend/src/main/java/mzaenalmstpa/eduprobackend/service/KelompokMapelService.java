package mzaenalmstpa.eduprobackend.service;

import mzaenalmstpa.eduprobackend.model.request.KelompokMapelRequest;
import mzaenalmstpa.eduprobackend.model.response.KelompokMapelResponse;

import java.util.List;
import java.util.Optional;

public interface KelompokMapelService {
    List<KelompokMapelResponse> getAll();
    Optional<KelompokMapelResponse> getById(Integer id, String kode);
    Optional<KelompokMapelResponse> save(KelompokMapelRequest request);
    Optional<KelompokMapelResponse> update(KelompokMapelRequest request, Integer id, String kode);
    Optional<KelompokMapelResponse> delete(Integer id, String kode);
}
