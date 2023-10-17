package com.bootcamp.serviceImpl;

import com.bootcamp.entity.GedungEntity;
import com.bootcamp.entity.RuangEntity;
import com.bootcamp.model.GedungModel;
import com.bootcamp.model.RuangModel;
import com.bootcamp.repository.GedungRepo;
import com.bootcamp.repository.RuangRepo;
import com.bootcamp.service.RuangService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.model.IModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RuangServiceImpl implements RuangService {
    private final GedungRepo gedungRepo;
    private final RuangRepo ruangRepo;

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

        GedungEntity gedung = gedungRepo.findById(request.getGedungId()).orElse(null);
        if(gedung == null){
            return Optional.empty();
        }

        RuangEntity entity = new RuangEntity(request, gedung);
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

        RuangEntity data = entity.get();
        data.setCode(request.getCode());
        data.setName(request.getName());
        GedungEntity gedung = new GedungEntity(request.getGedungId());
        data.setGedung(gedung);
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
