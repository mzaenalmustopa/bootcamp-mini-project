package com.mznalmstpa.springjpa.serviceImpl;

import com.mznalmstpa.springjpa.entity.AssessmentEntity;
import com.mznalmstpa.springjpa.model.AssessmentModel;
import com.mznalmstpa.springjpa.repository.AssessmentRepo;
import com.mznalmstpa.springjpa.service.AssessmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService {
    private final AssessmentRepo assessmentRepo;

    @Override
    public List<AssessmentModel> getAll() {
        List<AssessmentEntity> result = this.assessmentRepo.findAll();
        if (result.isEmpty()){
            return Collections.emptyList();
        }

        return result.stream()
                .map(AssessmentModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AssessmentModel> getById(Long id) {
        AssessmentEntity assessment = this.assessmentRepo.findById(id).orElse(null);
        if (assessment == null){
            return Optional.empty();
        }

        AssessmentModel result = new AssessmentModel(assessment);
        return Optional.of(result);
    }
}
