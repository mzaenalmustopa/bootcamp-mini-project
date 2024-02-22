package com.demo.quickcount.repository;

import com.demo.quickcount.model.entity.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepo extends JpaRepository<SurveyEntity, String>{
}
