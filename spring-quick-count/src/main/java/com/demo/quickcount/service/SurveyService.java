package com.demo.quickcount.service;

import com.demo.quickcount.model.request.SurveyRequest;
import com.demo.quickcount.model.response.SurveyDataResponse;
import com.demo.quickcount.model.response.SurveyResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SurveyService {
    List<SurveyResponse> getAll();
    Map<String, SurveyResponse> getMap();
    Optional<SurveyResponse> getById(String id);
    Optional<SurveyResponse> save(SurveyRequest request);
    Optional<SurveyResponse> update(SurveyRequest request, String id);
    Optional<SurveyResponse> delete(String id);
}
