package com.bootcamp.serviceImpl;

import com.bootcamp.entity.RuangEntity;
import com.bootcamp.model.GedungModel;
import com.bootcamp.model.RuangModel;
import com.bootcamp.repository.RuangRepo;
import com.bootcamp.service.RuangService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.model.IModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RuangServiceImpl implements RuangService {

    private final RuangRepo ruangRepo;

    public RuangServiceImpl(RuangRepo ruangRepo) {
        this.ruangRepo = ruangRepo;
    }

    @Override
    public List<RuangModel> getAll() {
        return ruangRepo.findAll().stream().map(RuangModel::new).collect(Collectors.toList());
    }

    @Override
    public RuangModel getById(String id) {
        return ruangRepo.findById(id).map(RuangModel::new).orElse(new RuangModel());
    }

    @Override
    public Optional<RuangModel> save(RuangModel request) {
        if (request == null){
            return Optional.empty();
        }

        RuangEntity entity = new RuangEntity(request);
        try {
            this.ruangRepo.save(entity);
            log.info("Save Ruang to database successfully");
            return Optional.of(new RuangModel(entity));
        }catch (Exception e){
            log.error("Save Ruang to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<RuangModel> update(String id, RuangModel request) {
        Optional<RuangEntity> entity = this.ruangRepo.findById(id);
        if (entity == null){
            return Optional.empty();
        }

        RuangEntity data = new RuangEntity();
        BeanUtils.copyProperties(request, data);
        data.setCreatedAt(LocalDateTime.now());
        data.setCreatedBy("SYSTEM");
        data.setUpdateAt(LocalDateTime.now());
        data.setUpdateBy("SYSTEM");
        try {
            this.ruangRepo.save(data);
            log.info("Update ruang to database Successfully");
            return Optional.of(new RuangModel(data));
        }catch (Exception e){
            log.error("Update ruang to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<RuangModel> delete(String id) {
        RuangEntity entity = ruangRepo.findById(id).orElse(new RuangEntity());
        if (entity == null){
            return Optional.empty();
        }

        try {
            ruangRepo.delete(entity);
            log.info("delete ruang to database successfully");
            return Optional.of(new RuangModel(entity));
        }catch (Exception e){
            log.error("delete ruang to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }
}
