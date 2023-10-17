package com.bootcamp.repository;

import com.bootcamp.entity.KelasDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KelasDetailRepo extends JpaRepository<KelasDetailEntity, String> {
}
