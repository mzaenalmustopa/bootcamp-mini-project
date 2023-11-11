package com.mznalmstpa.springjpa.repository;

import com.mznalmstpa.springjpa.entity.AssessmentOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentOptionRepo extends JpaRepository<AssessmentOptionEntity, Long> {
}
