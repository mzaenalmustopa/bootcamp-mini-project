package com.demo.quickcount.service;

import com.demo.quickcount.model.request.SurveyDetailRequest;
import com.demo.quickcount.model.response.SurveyDetailResponse;

import java.util.List;
import java.util.Optional;

public interface SurveyDetailService {
    List<SurveyDetailResponse> getAll();
    Optional<SurveyDetailResponse> getById(String id);
    Optional<SurveyDetailResponse> save(SurveyDetailRequest request);
    Optional<SurveyDetailResponse> update(SurveyDetailRequest request, String id);
    Optional<SurveyDetailResponse> delete(String id);
}
