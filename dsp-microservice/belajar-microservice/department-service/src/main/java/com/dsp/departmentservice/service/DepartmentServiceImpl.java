package com.dsp.departmentservice.service;

import com.dsp.departmentservice.model.entity.DepartmentEntity;
import com.dsp.departmentservice.model.request.DepartmentRequest;
import com.dsp.departmentservice.model.response.DepartmentResponse;
import com.dsp.departmentservice.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService{
    private DepartmentRepository repository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DepartmentResponse> getAll() {
        var result = this.repository.findAll();
        log.info("Get department is empty, data: {}", result);
        if(result.isEmpty()){
            return Collections.emptyList();
        }

        return result.stream()
                .map(this::setResponse)
                .collect(Collectors.toList());
    }

    private DepartmentResponse setResponse(DepartmentEntity entity){
        DepartmentResponse result = new DepartmentResponse();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    @Override
    public Optional<DepartmentResponse> getById(String id) {
        var result = this.repository.findById(id).orElse(null);
        log.info("Get department by id, data: {}", result);
        if(result == null) {
            return Optional.empty();
        }
        return Optional.of(this.setResponse(result));
    }

    @Override
    public Optional<DepartmentResponse> save(DepartmentRequest request) {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setId(UUID.randomUUID().toString());
        return saveDepartment(request, entity);
    }

    @Override
    public Optional<DepartmentResponse> update(String id, DepartmentRequest request) {
        DepartmentEntity entity = repository.findById(id).orElse(null);
        if(entity == null){
            log.error("Get department by id: {} not found", id);
            return Optional.empty();
        }

        return saveDepartment(request, entity);
    }

    private Optional<DepartmentResponse> saveDepartment(DepartmentRequest request, DepartmentEntity entity) {
        BeanUtils.copyProperties(request, entity);
        try{
            this.repository.save(entity);
            DepartmentResponse result = this.setResponse(entity);
            log.info("Save department success, data: {}", result);
            return Optional.of(result);
        } catch (Exception e){
            log.error("Save department failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<DepartmentResponse> delete(String id) {
        DepartmentEntity entity = repository.findById(id).orElse(null);
        if(entity == null){
            log.error("Get department by id: {} not found", id);
            return Optional.empty();
        }

        try{
            this.repository.delete(entity);
            DepartmentResponse result = this.setResponse(entity);
            log.info("Delete department success, data: {}", result);
            return Optional.of(result);
        } catch (Exception e){
            log.error("Delete department failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
