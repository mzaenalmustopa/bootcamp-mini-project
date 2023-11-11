package com.mznalmstpa.springjpa.repository;

import com.mznalmstpa.springjpa.entity.AssessmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepo extends JpaRepository<AssessmentEntity, Long> {
}
