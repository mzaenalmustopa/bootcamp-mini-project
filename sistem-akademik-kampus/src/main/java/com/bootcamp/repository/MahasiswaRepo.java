package com.bootcamp.repository;

import com.bootcamp.entity.MahasiswaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MahasiswaRepo extends JpaRepository<MahasiswaEntity, String> {
}
