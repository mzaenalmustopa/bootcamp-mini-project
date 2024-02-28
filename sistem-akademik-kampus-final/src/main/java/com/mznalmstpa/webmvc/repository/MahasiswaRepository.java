package com.mznalmstpa.webmvc.repository;

import com.mznalmstpa.webmvc.entity.MahasiswaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MahasiswaRepository extends JpaRepository<MahasiswaEntity, String> {
    List<MahasiswaEntity> findByNim(String nim);
    List<MahasiswaEntity> findByName(String name);
    List<MahasiswaEntity> findByNimAndName(String nim, String name);
}
