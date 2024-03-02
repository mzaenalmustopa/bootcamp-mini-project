package mzaenalmstpa.eduprobackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mzaenalmstpa.eduprobackend.constant.DataStatus;
import mzaenalmstpa.eduprobackend.constant.MessageApp;
import mzaenalmstpa.eduprobackend.exception.CommonApiException;
import mzaenalmstpa.eduprobackend.model.entity.GedungEntity;
import mzaenalmstpa.eduprobackend.model.request.GedungRequest;
import mzaenalmstpa.eduprobackend.model.response.GedungResponse;
import mzaenalmstpa.eduprobackend.repository.GedungRepo;
import mzaenalmstpa.eduprobackend.service.GedungService;
import org.hibernate.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GedungServiceImpl implements GedungService {

    private final GedungRepo gedungRepo;

    @Override
    public List<GedungResponse> getAll() {
        List<GedungEntity> result = this.gedungRepo.findAllByStatus(DataStatus.AKTIF);
        if (result.isEmpty()){
            return Collections.emptyList();
        }

        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<GedungResponse> geyById(String kode) {
        GedungEntity result = this.getEntityById(kode);
        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<GedungResponse> save(GedungRequest request) {
        if (gedungRepo.existsByKode(request.getKode())){
            log.info("Save Gedung gagal, terjadi error : kode sudah di gunakan");
            Map<String, String> errors = Map.of("Kode","Kode" + request.getKode() + " sudah digunakan");
            throw new CommonApiException("Save Failed", HttpStatus.BAD_REQUEST, errors);
        }
        
        GedungEntity result = this.convertReqToEntity(request);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<GedungResponse> update(GedungRequest request, String kode) {
        GedungEntity result = this.getEntityById(kode);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<GedungResponse> delete(String kode) {
        GedungEntity result = this.getEntityById(kode);

        result.setDeleteAt(LocalDateTime.now());
        result.setStatus(DataStatus.DIHAPUS);
        return saveOrUpdate(result);
    }

    private Optional<GedungResponse> saveOrUpdate(GedungEntity result){
        try {
            this.gedungRepo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (DataIntegrityViolationException e){
            log.error("Save Gedung, SQL error : {}",e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        } catch (Exception e){
            log.error("Save Gedung gagal, terjadi error :{}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private GedungEntity getEntityById(String id){
        GedungEntity result = this.gedungRepo.findById(id).orElse(null);
        if (result == null){
            Map<String, String> errors = Map.of("kode","kode" + id + "tidak di temukan");
            throw new CommonApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }
        
        return result;
    }
    
    private GedungResponse convertEntityToRes(GedungEntity entity){
        GedungResponse result = new GedungResponse();
        BeanUtils.copyProperties(entity, result);
        return result;
    }
    
    private GedungEntity convertReqToEntity(GedungRequest request){
        GedungEntity result = new GedungEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdateAt(LocalDateTime.now());;
        return result;
    }
    
    private void convertReqToEntity(GedungRequest request, GedungEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdateAt(LocalDateTime.now());
    }
}
