package com.mznalmstpa.springjpa.service;

import com.mznalmstpa.springjpa.model.AssessmentModel;

import java.util.List;
import java.util.Optional;

public interface AssessmentService {
    List<AssessmentModel> getAll();
    Optional<AssessmentModel> getById(Long id);
}
