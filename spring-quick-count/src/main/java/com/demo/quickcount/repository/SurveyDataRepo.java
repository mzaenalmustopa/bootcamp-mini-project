package com.demo.quickcount.repository;

import com.demo.quickcount.model.entity.SurveyDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyDataRepo extends JpaRepository<SurveyDataEntity, String> {
}
