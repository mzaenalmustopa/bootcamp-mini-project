package com.mznalmstpa.springjpa.repository;

import com.mznalmstpa.springjpa.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepo extends JpaRepository<DistrictEntity, Long> {
    Optional<DistrictEntity> findByCode(String code);
    List<DistrictEntity> findByProvinceId(Long provinceId);
}
