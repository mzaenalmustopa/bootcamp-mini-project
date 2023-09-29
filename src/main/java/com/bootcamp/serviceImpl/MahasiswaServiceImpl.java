package com.bootcamp.serviceImpl;

import com.bootcamp.entity.MahasiswaEntity;
import com.bootcamp.model.MahasiswaModel;
import com.bootcamp.repository.MahasiswaRepo;
import com.bootcamp.service.MahasiswaService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class MahasiswaServiceImpl implements MahasiswaService {

    private MahasiswaRepo mahasiswaRepo;

    public MahasiswaServiceImpl(MahasiswaRepo mahasiswaRepo) {
        this.mahasiswaRepo = mahasiswaRepo;
    }

    @Override
    public List<MahasiswaModel> getAll() {
        return this.mahasiswaRepo.findAll().stream().map(MahasiswaModel::new).collect(Collectors.toList());
    }

    @Override
    public MahasiswaModel getById(String id) {
        if (id == null){
            return new MahasiswaModel();
        }
        return this.mahasiswaRepo.findById(id).map(MahasiswaModel::new).orElse(new MahasiswaModel());
    }

    @Override
    public Optional<MahasiswaModel> save(MahasiswaModel request) {
        if ( request == null){
            return Optional.empty();
        }

        MahasiswaEntity entity = new MahasiswaEntity(request);
        try {
            this.mahasiswaRepo.save(entity);
            log.info("Save Mahasiswa to database successfully");
            return Optional.of(new MahasiswaModel(entity));
        }catch (Exception e){
            log.error("Save Mahasiswa to database failed, error:{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<MahasiswaModel> update(String id, MahasiswaModel request) {

        Optional<MahasiswaEntity> entity = this.mahasiswaRepo.findById(id);
        if (entity.isEmpty()){
            return Optional.empty();
        }

        MahasiswaEntity data = entity.get();
        BeanUtils.copyProperties(request, data);
        data.setCreatedAt(LocalDateTime.now());
        data.setCreatedBy("SYSTEM");
        data.setUpdateAt(LocalDateTime.now());
        data.setUpdateBy("SYSTEM");
        try {
            this.mahasiswaRepo.save(data);
            log.info("update Mahasiswa to database successfully");
            return Optional.of(new MahasiswaModel(data));
        }catch (Exception e){
            log.error("Update Mahasiswa to database failed , error:{}",e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public Optional<MahasiswaModel> delete(String id) {
        MahasiswaEntity entity = mahasiswaRepo.findById(id).orElse(new MahasiswaEntity());
        if (entity == null){
            return Optional.empty();
        }

        try {
            mahasiswaRepo.delete(entity);
            log.info("delete mahasiswa to database successfully");
            return Optional.of(new MahasiswaModel(entity));
        }catch (Exception e){
            log.error("delete mahasiswa to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }
}
