package com.bootcamp.serviceImpl;

import com.bootcamp.entity.FakultasEntity;
import com.bootcamp.entity.JurusanEntity;
import com.bootcamp.model.JurusanModel;
import com.bootcamp.repository.FakultasRepo;
import com.bootcamp.repository.JurusanRepo;
import com.bootcamp.service.JurusanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class JurusanServiceImpl implements JurusanService {

    private final JurusanRepo jurusanRepo;
    private final FakultasRepo fakultasRepo;

    @Override
    public List<JurusanModel> getAll() {
        return this.jurusanRepo.findAll().stream().map(JurusanModel::new).collect(Collectors.toList());
    }

    @Override
    public JurusanModel getById(String id) {
        if (id == null){
            return new JurusanModel();
        }
        return this.jurusanRepo.findById(id).map(JurusanModel::new).orElse(new JurusanModel());
    }

    @Override
    public Optional<JurusanModel> save(JurusanModel request) {

        if (request == null){
            return Optional.empty();
        }

        FakultasEntity fakultas = fakultasRepo.findById(request.getFakultasId()).orElse(null);
        if (fakultas == null){
            return Optional.empty();
        }

        JurusanEntity entity = new JurusanEntity(request, fakultas);
        try {
            this.jurusanRepo.save(entity);
            log.info("Save jurusan to database successfully");
            return Optional.of(new JurusanModel(entity));
        }catch (Exception e){
            log.error("Save jurusan to database failed :{} error",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<JurusanModel> update(String id, JurusanModel request) {
        Optional<JurusanEntity> entity = this.jurusanRepo.findById(id);
        if (entity.isEmpty()){
            return Optional.empty();
        }

        JurusanEntity data = entity.get();
        data.setCode(request.getCode());
        data.setName(request.getName());
        FakultasEntity fakultas = new FakultasEntity(request.getFakultasId());
        data.setFakultas(fakultas);

        try {
            this.jurusanRepo.save(data);
            log.info("Update Jurusan to database is successfully");
            return Optional.of(new JurusanModel(data));
        }catch (Exception e){
            log.error("Update jurusan to database failed : error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<JurusanModel> delete(String id) {

        JurusanEntity entity = jurusanRepo.findById(id).orElse(new JurusanEntity());
        if (entity == null){
            log.warn("jurusan with id: {} not found ", id);
            return Optional.empty();
        }

        try {
            jurusanRepo.delete(entity);
            log.info("Delete Jurusan to database Successfully");
            return Optional.of(new JurusanModel(entity));
        }catch (Exception e){
            log.error("Delete Jurusan to database failed, error{}", e.getMessage());
            return Optional.empty();
        }
    }
}
