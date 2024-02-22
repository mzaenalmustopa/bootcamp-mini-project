package com.demo.quickcount.repository;

import com.demo.quickcount.model.entity.SurveyDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyDetailRepo extends JpaRepository<SurveyDetailEntity, String> {
}
