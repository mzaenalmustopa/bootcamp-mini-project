package com.bootcamp.serviceImpl;

import com.bootcamp.entity.KelasEntity;
import com.bootcamp.model.KelasModel;
import com.bootcamp.repository.KelasRepo;
import com.bootcamp.service.KelasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class KelasServiceImpl implements KelasService {

    private KelasRepo kelasRepo;

    public KelasServiceImpl(KelasRepo kelasRepo) {
        this.kelasRepo = kelasRepo;
    }

    @Override
    public List<KelasModel> getAll() {
        return kelasRepo.findAll().stream().map(KelasModel::new).collect(Collectors.toList());
    }

    @Override
    public KelasModel getById(String id) {
        if (id == null){
            return new KelasModel();
        }
        return kelasRepo.findById(id).map(KelasModel::new).orElse(new KelasModel());
    }

    @Override
    public Optional<KelasModel> save(KelasModel request) {

        if (request == null){
            return Optional.empty();
        }

        KelasEntity entity = new KelasEntity(request);
        try {
            kelasRepo.save(entity);
            log.info("Save Kelas to database successfully ");
            return Optional.of(new KelasModel(entity));
        }catch (Exception e){
            log.error("Save Kelas to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelasModel> update(String id, KelasModel request) {

        Optional<KelasEntity> entity = this.kelasRepo.findById(id);
        if (entity.isEmpty()){
            return Optional.empty();
        }

        KelasEntity data = entity.get();
        BeanUtils.copyProperties(request, data);
        data.setCreatedAt(LocalDateTime.now());
        data.setCreatedBy("SYSTEM");
        data.setUpdateAt(LocalDateTime.now());
        data.setUpdateBy("SYSTEM");
        try {
            this.kelasRepo.save(data);
            log.info("Update kelas to database successfully");
            return Optional.of(new KelasModel(data));
        }catch (Exception e){
            log.error("Update kelas to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelasModel> delete(String id) {
        KelasEntity entity = this.kelasRepo.findById(id).orElse(new KelasEntity());
        if (entity == null){
            log.warn("Kelas with id{}, not found", id);
            return Optional.empty();
        }

        try {
            kelasRepo.delete(entity);
            log.info("Delete kelas to database successfully");
            return Optional.empty();
        }catch (Exception e){
            log.error("Delete kelas to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }
}
