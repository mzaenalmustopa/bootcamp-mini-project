package com.bootcamp.northwind.service.serviceImpl;

import com.bootcamp.northwind.model.entity.LookupEntity;
import com.bootcamp.northwind.repository.LookupRepo;
import com.bootcamp.northwind.service.LookupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LookupServiceImpl implements LookupService {
    private final LookupRepo lookupRepo;

    @Autowired
    public LookupServiceImpl(LookupRepo lookupRepo) {
        this.lookupRepo = lookupRepo;
    }

    @Override
    public List<LookupEntity> getByGroup(String group) {
        if (group == null || group.isEmpty()) {
            return Collections.emptyList();
        }
        return this.lookupRepo.findByGroups(group);
    }

    @Override
    public Optional<LookupEntity> getByCode(String code) {
        if (code == null || code.isEmpty())
            return Optional.empty();

        return this.lookupRepo.findByCode(code);
    }

    @Override
    public Optional<LookupEntity> getById(String id) {
        if (id == null || id.isEmpty())
            return Optional.empty();
        return this.lookupRepo.findById(id);
    }

    @Override
    public Optional<LookupEntity> save(LookupEntity entity) {
        if (entity ==null)
            return Optional.empty();
        try {
            this.lookupRepo.save(entity);
            return Optional.of(entity);
        }catch (Exception e){
            log.error("Failed save lookup, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<LookupEntity> saveAll(List<LookupEntity> entities) {
        if (entities.isEmpty())
            return Collections.emptyList();
        try {
            this.lookupRepo.saveAll(entities);
            return entities;
        } catch (Exception e){
            log.error("Failed save lookup, error: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
}
