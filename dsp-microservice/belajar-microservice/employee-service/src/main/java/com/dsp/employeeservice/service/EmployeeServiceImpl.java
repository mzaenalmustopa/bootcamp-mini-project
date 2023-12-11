package com.dsp.employeeservice.service;

import com.dsp.employeeservice.model.entity.EmployeeEntity;
import com.dsp.employeeservice.model.request.EmployeeRequest;
import com.dsp.employeeservice.model.response.EmployeeResponse;
import com.dsp.employeeservice.repository.EmployeeRepository;
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
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EmployeeResponse> getAll() {
        var result = this.repository.findAll();
        log.info("Get Employee is empty, data: {}", result);
        if(result.isEmpty()){
            return Collections.emptyList();
        }

        return result.stream()
                .map(this::setResponse)
                .collect(Collectors.toList());
    }

    private EmployeeResponse setResponse(EmployeeEntity entity){
        EmployeeResponse result = new EmployeeResponse();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    @Override
    public Optional<EmployeeResponse> getById(String id) {
        var result = this.repository.findById(id).orElse(null);
        log.info("Get Employee by id, data: {}", result);
        if(result == null) {
            return Optional.empty();
        }
        return Optional.of(this.setResponse(result));
    }

    @Override
    public List<EmployeeResponse> getByDepartmentId(String id) {
        var result = this.repository.findByDepartmentId(id);
        log.info("Get Employee by department id:{} is empty, data: {}", id, result);
        if(result.isEmpty()){
            return Collections.emptyList();
        }

        return result.stream()
                .map(this::setResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeResponse> save(EmployeeRequest request) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(UUID.randomUUID().toString());
        return saveEmployee(request, entity);
    }

    @Override
    public Optional<EmployeeResponse> update(String id, EmployeeRequest request) {
        EmployeeEntity entity = repository.findById(id).orElse(null);
        if(entity == null){
            log.error("Get Employee by id: {} not found", id);
            return Optional.empty();
        }

        return saveEmployee(request, entity);
    }

    private Optional<EmployeeResponse> saveEmployee(EmployeeRequest request, EmployeeEntity entity) {
        BeanUtils.copyProperties(request, entity);
        try{
            this.repository.save(entity);
            EmployeeResponse result = this.setResponse(entity);
            log.info("Save Employee success, data: {}", result);
            return Optional.of(result);
        } catch (Exception e){
            log.error("Save Employee failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<EmployeeResponse> delete(String id) {
        EmployeeEntity entity = repository.findById(id).orElse(null);
        if(entity == null){
            log.error("Get Employee by id: {} not found", id);
            return Optional.empty();
        }

        try{
            this.repository.delete(entity);
            EmployeeResponse result = this.setResponse(entity);
            log.info("Delete Employee success, data: {}", result);
            return Optional.of(result);
        } catch (Exception e){
            log.error("Delete Employee failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
