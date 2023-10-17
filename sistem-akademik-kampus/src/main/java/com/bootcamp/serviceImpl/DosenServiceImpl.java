package com.bootcamp.serviceImpl;


import com.bootcamp.entity.DosenEntity;
import com.bootcamp.model.DosenModel;
import com.bootcamp.repository.DosenRepo;
import com.bootcamp.service.DosenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DosenServiceImpl implements DosenService {

    private DosenRepo dosenRepo;

    public DosenServiceImpl(DosenRepo dosenRepo) {
        this.dosenRepo = dosenRepo;
    }

    @Override
    public List<DosenModel> getAll() {
        return this.dosenRepo.findAll().stream().map(DosenModel::new).collect(Collectors.toList());
    }

    @Override
    public DosenModel getById(String id) {
        if (id == null){
            return new DosenModel();
        }
        return this.dosenRepo.findById(id).map(DosenModel::new).orElse(new DosenModel());
    }

    @Override
    public Optional<DosenModel> save(DosenModel request) {
        if (request == null){
            return Optional.empty();
        }

        DosenEntity result = new DosenEntity(request);
        try {
            this.dosenRepo.save(result);
            log.info("Save Dosen to database successfully");
            return Optional.of(new DosenModel(result));
        }catch (Exception e){
            log.error("Save dosen to database failed:{} error", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<DosenModel> update(String id, DosenModel request) {

        Optional<DosenEntity> entity = this.dosenRepo.findById(id);
        if (entity.isEmpty()){
            return Optional.empty();
        }

        DosenEntity data = entity.get();
        BeanUtils.copyProperties(request, data);
        data.setId(id);
        try {
            this.dosenRepo.save(data);
            log.info("update dosen to database successfully");
            return Optional.of(new DosenModel(data));
        }catch (Exception e){
            log.error("update dosen to database failed , error:{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<DosenModel> delete(String id) {
        DosenEntity entity = dosenRepo.findById(id).orElse(new DosenEntity());
        if (entity == null){
            log.warn("Dosen with id :{} not found", id);
            return Optional.empty();
        }

        try {
            dosenRepo.delete(entity);
            log.info("delete Dosen to database successfully");
            return Optional.of(new DosenModel(entity));
        }catch (Exception e){
            log.error("Delete Dosen to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }
}
