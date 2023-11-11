package com.mznalmstpa.springjpa.repository;

import com.mznalmstpa.springjpa.entity.SubDistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubDistrictRepo extends JpaRepository<SubDistrictEntity, Long> {
    List<SubDistrictEntity> findByDistrictId(Long districtId);
}
