package com.demo.quickcount.service;

import com.demo.quickcount.model.entity.SurveyDataEntity;
import com.demo.quickcount.model.request.SurveyDataRequest;
import com.demo.quickcount.model.response.SurveyDataResponse;
import com.demo.quickcount.repository.SurveyDataRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SurveyDataServiceImpl implements SurveyDataService{
    private final SurveyDataRepo repo;

    @Override
    public List<SurveyDataResponse> getAll() {
        List<SurveyDataEntity> SurveyDataEntities = repo.findAll();
        if (SurveyDataEntities.isEmpty()) {
            return Collections.emptyList();
        }
        return SurveyDataEntities.stream().map(this::convertToResponse).toList();
    }

    @Override
    public Optional<SurveyDataResponse> getById(String id) {
        SurveyDataEntity result = this.repo.findById(id).orElse(null);
        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(convertToResponse(result));
    }

    @Override
    public Optional<SurveyDataResponse> save(SurveyDataRequest request) {
        try {
            SurveyDataEntity result = this.repo.save(convertToEntity(request));
            return Optional.of(convertToResponse(result));
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<SurveyDataResponse> update(SurveyDataRequest request, String id) {
        SurveyDataEntity result = this.repo.findById(id).orElse(null);
        if (result == null) {
            return Optional.empty();
        }

        try {
            BeanUtils.copyProperties(request, result);
            this.repo.save(result);
            return Optional.of(convertToResponse(result));
        }catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<SurveyDataResponse> delete(String id) {
        SurveyDataEntity result = this.repo.findById(id).orElse(null);
        if (result == null) {
            return Optional.empty();
        }
        try {
            this.repo.delete(result);
            return Optional.of(convertToResponse(result));
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    private SurveyDataResponse convertToResponse(SurveyDataEntity entity){
        SurveyDataResponse response = new SurveyDataResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    private SurveyDataEntity convertToEntity(SurveyDataRequest request) {
        SurveyDataEntity entity = new SurveyDataEntity();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }
}
