package com.bootcamp.serviceImpl;

import com.bootcamp.entity.KelasDetailEntity;
import com.bootcamp.entity.KelasEntity;
import com.bootcamp.entity.MahasiswaEntity;
import com.bootcamp.model.KelasDetailModel;
import com.bootcamp.repository.KelasDetailRepo;
import com.bootcamp.service.KelasDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class KelasDetailServiceImpl implements KelasDetailService {

    private final KelasDetailRepo kelasDetailRepo;

    @Override
    public List<KelasDetailModel> getAll() {
        List<KelasDetailEntity> result = this.kelasDetailRepo.findAll();
        if (result.isEmpty()){
            return Collections.emptyList();
        }
        return this.kelasDetailRepo.findAll().stream().map(KelasDetailModel::new).collect(Collectors.toList());
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

        KelasDetailEntity result =  new KelasDetailEntity(request);
        try {
            this.kelasDetailRepo.save(result);
            log.info("Save kelas detail success");
            return Optional.of(new KelasDetailModel(result));
        }catch (Exception e){
            log.error("Save Kelas detail failed, error:{}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelasDetailModel> update(KelasDetailModel request, String id) {
       Optional <KelasDetailEntity> kelasDetail = this.kelasDetailRepo.findById(id);
        if(kelasDetail.isEmpty()){
            return Optional.empty();
        }

        KelasDetailEntity data = kelasDetail.get();
        BeanUtils.copyProperties(request, data);
        KelasEntity kelas = new KelasEntity(request.getKelas().getId());
        data.setKelas(kelas);
        MahasiswaEntity mahasiswa = new MahasiswaEntity(request.getMahasiswa().getId());
        data.setMahasiswa(mahasiswa);

        try {
            this.kelasDetailRepo.save(data);
            log.info("update kelasDetail Success");
            return Optional.of(new KelasDetailModel(data));
        }catch (Exception e){
            log.error("update kelasDetail error:{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelasDetailModel> delete(String id) {
        Optional<KelasDetailEntity> result = this.kelasDetailRepo.findById(id);
        if (result.isEmpty()){
            return Optional.empty();
        }

        try {
            KelasDetailEntity data = result.get();
            this.kelasDetailRepo.delete(data);
            log.info("delete kelasDetail Success");
            return Optional.of(new KelasDetailModel(data));
        }catch (Exception e){
            log.error("delete kelasDetail failed, error:{}",e.getMessage());
            return Optional.empty();
        }
    }
}
