package com.bootcamp.serviceImpl;

import com.bootcamp.entity.KelasDetailEntity;
import com.bootcamp.entity.KelasEntity;
import com.bootcamp.entity.MahasiswaEntity;
import com.bootcamp.model.KelasDetailModel;
import com.bootcamp.repository.KelasDetailRepo;
import com.bootcamp.repository.KelasRepo;
import com.bootcamp.repository.MahasiswaRepo;
import com.bootcamp.service.KelasDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class KelasDetailServiceImpl implements KelasDetailService {

    private final KelasDetailRepo kelasDetailRepo;
    private final KelasRepo kelasRepo;
    private final MahasiswaRepo mahasiswaRepo;

    @Override
    public List<KelasDetailModel> getAll() {
        List<KelasDetailEntity> result = this.kelasDetailRepo.findAll();
        if (result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(KelasDetailModel::new).collect(Collectors.toList());
    }

    @Override
    public KelasDetailModel getById(String id) {
        if (id == null){
            return new KelasDetailModel();
        }
        return this.kelasDetailRepo.findById(id).map(KelasDetailModel::new).orElse(new KelasDetailModel());
    }

    @Override
    public Optional<KelasDetailModel> save(KelasDetailModel request) {

        if (request == null){
            return Optional.empty();
        }

        KelasEntity kelas = this.kelasRepo.findById(request.getKelasId()).orElse(null);
        if (kelas == null){
            return Optional.empty();
        }

        MahasiswaEntity mahasiswa = this.mahasiswaRepo.findById(request.getMahasiswaId()).orElse(null);
        if (mahasiswa == null){
            return Optional.empty();
        }

        KelasDetailEntity result =  new KelasDetailEntity(request);
        try {
            kelasDetailRepo.save(result);
            log.info("Save kelas detail success");
            return Optional.of(new KelasDetailModel(result));
        }catch (Exception e){
            log.error("Save Kelas detail failed, error:{}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelasDetailModel> update(String id, KelasDetailModel request) {
       KelasDetailEntity entity = this.kelasDetailRepo.findById(id).orElse(null);
       if (entity == null){
           return Optional.empty();
       }


        BeanUtils.copyProperties(request, entity);
        KelasEntity kelas = kelasRepo.findById(request.getKelasId()).orElse(null);
        if (request == null){
            return Optional.empty();
        }
        MahasiswaEntity mahasiswa = mahasiswaRepo.findById(request.getMahasiswaId()).orElse(null);
        if (request == null){
            return Optional.empty();
        }

        entity.setId(id);
        entity.setKelas(kelas);
        entity.setMahasiswa(mahasiswa);
        entity.setUpdateAt(LocalDateTime.now());

        try {
            this.kelasDetailRepo.save(entity);
            log.info("update kelasDetail Success");
            return Optional.of(new KelasDetailModel(entity));
        }catch (Exception e){
            log.error("update kelasDetail failed,  error:{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelasDetailModel> delete(String id) {
        KelasDetailEntity result = this.kelasDetailRepo.findById(id).orElse(new KelasDetailEntity());
        if (result == null){
            log.warn("KelasDetail with id{}, not found", id);
            return Optional.empty();
        }

        try {
            this.kelasDetailRepo.delete(result);
            log.info("delete kelasDetail Success");
            return Optional.of(new KelasDetailModel(result));
        } catch (Exception e){
            log.error("delete kelasDetail failed, error:{}",e.getMessage());
            return Optional.empty();
        }
    }
}
