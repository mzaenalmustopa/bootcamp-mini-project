package com.bootcamp.serviceImpl;

import com.bootcamp.entity.GedungEntity;
import com.bootcamp.model.GedungModel;
import com.bootcamp.repository.GedungRepo;
import com.bootcamp.service.GedungService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GedungServiceImpl implements GedungService {

    private GedungRepo gedungRepo;

    public GedungServiceImpl(GedungRepo gedungRepo) {
        this.gedungRepo = gedungRepo;
    }

    @Override
    public List<GedungModel> getAll() {
        return gedungRepo.findAll().stream().map(GedungModel::new).collect(Collectors.toList());
    }

    @Override
    public GedungModel getById(String id) {
        return gedungRepo.findById(id).map(GedungModel::new).orElse(new GedungModel());
    }

    @Override
    public Optional<GedungModel> save(GedungModel request) {
        if (request == null){
            return Optional.empty();
        }

        GedungEntity entity = new GedungEntity(request);
        try {
            this.gedungRepo.save(entity);
            log.info("save gedung to database successfully");
            return Optional.of(new GedungModel());
        }catch (Exception e){
            log.error("save gedung to database failed :{} error",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<GedungModel> update(String id, GedungModel request) {
        Optional<GedungEntity> entity = this.gedungRepo.findById(id);
        if (entity.isEmpty()){
            return Optional.empty();
        }

        GedungEntity data = entity.get();
        BeanUtils.copyProperties(request, data);
        data.setId(id);
        try {
            this.gedungRepo.save(data);
            log.info("update gedung to database successfully");
            return Optional.of(new GedungModel(data));
        }catch (Exception e){
            log.error("update gedung to database failed:{} error",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<GedungModel> delete(String id) {
        GedungEntity entity = gedungRepo.findById(id).orElse(new GedungEntity());
        if (entity == null){
            log.warn("gedung with id not found:{}", id);
            return Optional.empty();
        }

        try {
            gedungRepo.delete(entity);
            log.info("delete gedung to database successfully");
            return Optional.of(new GedungModel(entity));
        }catch (Exception e){
            log.error("delete gedung to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }
}
