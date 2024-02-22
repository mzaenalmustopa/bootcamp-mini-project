package com.demo.quickcount.service;

import com.demo.quickcount.model.entity.SurveyDataEntity;
import com.demo.quickcount.model.entity.SurveyDetailEntity;
import com.demo.quickcount.model.request.SurveyDataRequest;
import com.demo.quickcount.model.request.SurveyDetailRequest;
import com.demo.quickcount.model.response.SurveyDetailResponse;
import com.demo.quickcount.repository.SurveyDetailRepo;
import com.demo.quickcount.repository.SurveyRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SurveyDetailServiceImpl implements SurveyDetailService{
    private final SurveyDetailRepo repo;
    private final SurveyRepo surveyRepo;

    @Override
    public List<SurveyDetailResponse> getAll() {
        List<SurveyDetailEntity> SurveyDetailEntities = repo.findAll();
        if (SurveyDetailEntities.isEmpty()) {
            return Collections.emptyList();
        }
        return SurveyDetailEntities.stream().map(this::convertToResponse).toList();
    }

    @Override
    public Optional<SurveyDetailResponse> getById(String id) {
        SurveyDetailEntity result = this.repo.findById(id).orElse(null);
        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(convertToResponse(result));
    }

    @Override
    public Optional<SurveyDetailResponse> save(SurveyDetailRequest request) {
        try {
            SurveyDetailEntity result = convertToEntity(request);
            result.setId(UUID.randomUUID().toString());

            for (SurveyDataRequest surveyDataEntity : request.getSurveyData()) {
                SurveyDataEntity item = new SurveyDataEntity();
                BeanUtils.copyProperties(surveyDataEntity, item);
                item.setSurveyDetailId(result.getId());
                item.setId(UUID.randomUUID().toString());

                result.addSurveyData(item);
            }

            this.repo.save(result);
            return Optional.of(convertToResponse(result));
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<SurveyDetailResponse> update(SurveyDetailRequest request, String id) {
        SurveyDetailEntity result = this.repo.findById(id).orElse(null);
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
    public Optional<SurveyDetailResponse> delete(String id) {
        SurveyDetailEntity result = this.repo.findById(id).orElse(null);
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

    private SurveyDetailResponse convertToResponse(SurveyDetailEntity entity){
        SurveyDetailResponse response = new SurveyDetailResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    private SurveyDetailEntity convertToEntity(SurveyDetailRequest request) {
        SurveyDetailEntity entity = new SurveyDetailEntity();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }
}
