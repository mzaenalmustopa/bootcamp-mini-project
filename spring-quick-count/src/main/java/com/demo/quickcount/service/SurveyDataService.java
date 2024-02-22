package com.demo.quickcount.service;

import com.demo.quickcount.model.request.SurveyDataRequest;
import com.demo.quickcount.model.response.SurveyDataResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SurveyDataService {
    List<SurveyDataResponse> getAll();
    Optional<SurveyDataResponse> getById(String id);
    Optional<SurveyDataResponse> save(SurveyDataRequest request);
    Optional<SurveyDataResponse> update(SurveyDataRequest request, String id);
    Optional<SurveyDataResponse> delete(String id);
}
