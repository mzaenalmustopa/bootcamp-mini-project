package com.demo.quickcount.service;

import com.demo.quickcount.model.entity.SurveyEntity;
import com.demo.quickcount.model.request.SurveyRequest;
import com.demo.quickcount.model.response.SurveyDataResponse;
import com.demo.quickcount.model.response.SurveyResponse;
import com.demo.quickcount.repository.SurveyRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SurveyServiceImpl implements SurveyService{
    private final SurveyRepo repo;

    @Override
    public List<SurveyResponse> getAll() {
        List<SurveyEntity> surveyEntities = repo.findAll();
        if (surveyEntities.isEmpty()) {
            return Collections.emptyList();
        }
        return surveyEntities.stream().map(this::convertToResponse).toList();
    }

    @Override
    public Map<String, SurveyResponse> getMap() {
        List<SurveyEntity> surveyEntities = repo.findAll();
        if (surveyEntities.isEmpty()) {
            return Map.of();
        }
        return surveyEntities.stream().collect(Collectors.toMap(SurveyEntity::getName, e -> convertToResponse(e)));
    }

    @Override
    public Optional<SurveyResponse> getById(String id) {
        SurveyEntity result = this.repo.findById(id).orElse(null);
        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(convertToResponse(result));
    }

    @Override
    public Optional<SurveyResponse> save(SurveyRequest request) {
        try {
            SurveyEntity result = this.repo.save(convertToEntity(request));
            return Optional.of(convertToResponse(result));
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<SurveyResponse> update(SurveyRequest request, String id) {
        SurveyEntity result = this.repo.findById(id).orElse(null);
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
    public Optional<SurveyResponse> delete(String id) {
        SurveyEntity result = this.repo.findById(id).orElse(null);
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

    private SurveyResponse convertToResponse(SurveyEntity entity){
        SurveyResponse response = new SurveyResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    private SurveyEntity convertToEntity(SurveyRequest request) {
        SurveyEntity entity = new SurveyEntity();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }
}
