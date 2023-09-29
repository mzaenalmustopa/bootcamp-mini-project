package com.bootcamp.serviceImpl;


import com.bootcamp.entity.MatakuliahEntity;
import com.bootcamp.model.MatakuliahModel;
import com.bootcamp.repository.MatakuliahRepo;
import com.bootcamp.service.MatakuliahService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MatakuliahServiceImpl implements MatakuliahService {

    private MatakuliahRepo matakuliahRepo;

    public MatakuliahServiceImpl(MatakuliahRepo matakuliahRepo) {
        this.matakuliahRepo = matakuliahRepo;
    }

    @Override
    public List<MatakuliahModel> getAll() {
        return this.matakuliahRepo.findAll().stream().map(MatakuliahModel::new).collect(Collectors.toList());
    }

    @Override
    public MatakuliahModel getById(String id) {
        if (id == null){
            return new MatakuliahModel();
        }
        return this.matakuliahRepo.findById(id).map(MatakuliahModel::new).orElse(new MatakuliahModel());
    }

    @Override
    public Optional<MatakuliahModel> save(MatakuliahModel request) {
        if (request == null){
            return Optional.empty();
        }

        MatakuliahEntity entity = new MatakuliahEntity(request);
        try {
            this.matakuliahRepo.save(entity);
            log.info("save Matakuliah to database successfully");
            return Optional.of(new MatakuliahModel(entity));
        }catch (Exception e){
            log.error("Save matakuliah to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<MatakuliahModel> update(String id, MatakuliahModel request) {

        Optional<MatakuliahEntity> entity = this.matakuliahRepo.findById(id);
        if (entity.isEmpty()){
            return Optional.empty();
        }

        MatakuliahEntity data = entity.get();
        BeanUtils.copyProperties(request, data);
        data.setCreatedAt(LocalDateTime.now());
        data.setCreatedBy("SYSTEM");
        data.setUpdateAt(LocalDateTime.now());
        data.setUpdateBy("SYSTEM");
        try {
            this.matakuliahRepo.save(data);
            log.info("update matakuliah to database successfully");
            return Optional.of(new MatakuliahModel(data));
        }catch (Exception e){
            log.error("updae matakuliah to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<MatakuliahModel> delete(String id) {
        MatakuliahEntity entity = matakuliahRepo.findById(id).orElse(new MatakuliahEntity());
        if (entity == null){
            return Optional.empty();
        }

        try {
            matakuliahRepo.delete(entity);
            log.info("Delete matakuliah to database successfully");
            return Optional.of(new MatakuliahModel());
        }catch (Exception e){
            log.error("Delete matakuliah to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }
}
