package com.bootcamp.serviceImpl;

import com.bootcamp.entity.FakultasEntity;
import com.bootcamp.model.FakultasModel;
import com.bootcamp.repository.FakultasRepo;
import com.bootcamp.service.FakultasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FakultasServiceImpl implements FakultasService {

    private FakultasRepo fakultasRepo;

    public FakultasServiceImpl(FakultasRepo fakultasRepo) {
        this.fakultasRepo = fakultasRepo;
    }

    @Override
    public List<FakultasModel> getAll() {
        return this.fakultasRepo.findAll().stream().map(FakultasModel::new).collect(Collectors.toList());
    }

    @Override
    public FakultasModel getById(String id) {
        if (id == null){
            return new FakultasModel();
        }
        return this.fakultasRepo.findById(id).map(FakultasModel::new).orElse(new FakultasModel());
    }

    @Override
    public Optional<FakultasModel> save(FakultasModel request) {

        if (request == null){
            return Optional.empty();
        }

        FakultasEntity entity = new FakultasEntity(request);
        try{
            this.fakultasRepo.save(entity);
            log.info("save fakultas to database successfully");
            return Optional.of(new FakultasModel(entity));
        }catch (Exception e){
            log.error("save fakultas to database failed , error:{}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<FakultasModel> update(String id, FakultasModel request) {

        Optional<FakultasEntity> result = this.fakultasRepo.findById(id);
        if (result.isEmpty()){
            return Optional.empty();
        }

        FakultasEntity data = result.get();
        BeanUtils.copyProperties(request, data);
        data.setCreatedAt(LocalDateTime.now());
        data.setCreatedBy("SYSTEM");
        data.setUpdateAt(LocalDateTime.now());
        data.setUpdateBy("SYSTEM");
        try {
            this.fakultasRepo.save(data);
            log.info("update fakultas to database is successfully");
            return Optional.of(new FakultasModel(data));
        }catch (Exception e){
            log.error("Update fakultas to database failed: error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<FakultasModel> delete(String id) {
        FakultasEntity entity = fakultasRepo.findById(id).orElse(new FakultasEntity());
        if (entity == null){
            log.warn("Fakultas with id :{} not found", id);
            return null;
        }

        try {
            fakultasRepo.delete(entity);
            log.info("delete fakultas to database successfully");
            return Optional.of(new FakultasModel(entity));
        }catch (Exception e){
            log.error("Delete fakultas to database failed , error{}", e.getMessage());
            return Optional.empty();
        }
    }
}
