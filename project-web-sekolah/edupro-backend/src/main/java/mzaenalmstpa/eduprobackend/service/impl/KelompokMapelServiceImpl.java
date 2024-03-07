package mzaenalmstpa.eduprobackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mzaenalmstpa.eduprobackend.constant.DataStatus;
import mzaenalmstpa.eduprobackend.constant.MessageApp;
import mzaenalmstpa.eduprobackend.exception.CommonApiException;
import mzaenalmstpa.eduprobackend.model.entity.KelompokMapelEntity;
import mzaenalmstpa.eduprobackend.model.entity.KelompokMapelId;
import mzaenalmstpa.eduprobackend.model.request.KelompokMapelRequest;
import mzaenalmstpa.eduprobackend.model.response.KelompokMapelResponse;
import mzaenalmstpa.eduprobackend.repository.KelompokMapelRepo;
import mzaenalmstpa.eduprobackend.service.KelompokMapelService;
import org.hibernate.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class KelompokMapelServiceImpl implements KelompokMapelService {

    private final KelompokMapelRepo mapelRepo;

    @Override
    public List<KelompokMapelResponse> getAll() {
        List<KelompokMapelEntity> result = this.mapelRepo.findAllByStatus(DataStatus.AKTIF);
        if (result.isEmpty()){
            return Collections.emptyList();
        }

        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<KelompokMapelResponse> getById(Integer id, String kode) {
        KelompokMapelEntity result = this.getEntityById(new KelompokMapelId(id, kode));

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<KelompokMapelResponse> save(KelompokMapelRequest request) {
        var id = new KelompokMapelId(request.getIdLembaga(), request.getKode());
        if (mapelRepo.existsById(id)){
            Map<String, String> errors = Map.of("kode", "Id Lembaga" + request.getIdLembaga()
                    + " dan kode " + request.getKode());
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        KelompokMapelEntity entity = this.convertReqToEntity(request);
        return saveOrUpdate(entity);
    }

    @Override
    public Optional<KelompokMapelResponse> update(KelompokMapelRequest request, Integer id, String kode) {
        KelompokMapelEntity result = this.getEntityById(new KelompokMapelId(id, kode));

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<KelompokMapelResponse> delete(Integer id, String kode) {
        KelompokMapelEntity result = this.getEntityById(new KelompokMapelId(id, kode));

        result.setStatus(DataStatus.DIHAPUS);
        return saveOrUpdate(result);
    }

    private Optional<KelompokMapelResponse> saveOrUpdate(KelompokMapelEntity result){
        try {
            this.mapelRepo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Kelompok Mapel, SQL error : {}",e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        } catch (Exception e){
            log.error("Save Kelompok Mapel gagal, terjadi error : {}",e.getMessage());
            Map<String, String> erros = Map.of("sql", e.getCause().getMessage());
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, erros);
        }
    }

    private KelompokMapelEntity getEntityById(KelompokMapelId kelompokMapelId){
        KelompokMapelEntity result = this.mapelRepo.findById(kelompokMapelId).orElse(null);
        if (result == null){
            Map<String, String> errors = Map.of("kode","Id Lembaga" + kelompokMapelId.getIdLembaga() +
                    " dan kode " + kelompokMapelId.getKode() + "tidak ditemukan");
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private KelompokMapelResponse convertEntityToRes(KelompokMapelEntity entity){
        KelompokMapelResponse result = new KelompokMapelResponse();
        BeanUtils.copyProperties(entity, result);
        result.setIdLembaga(entity.getId().getIdLembaga());
        result.setKode(entity.getId().getKode());
        return result;
    }

    private KelompokMapelEntity convertReqToEntity(KelompokMapelRequest request){
        KelompokMapelId id = new KelompokMapelId(request.getIdLembaga(), request.getKode());
        return new KelompokMapelEntity(id, request.getNama(), DataStatus.AKTIF);
    }

    private void convertReqToEntity(KelompokMapelRequest request, KelompokMapelEntity result){
        result.setNama(request.getNama());
    }
}
