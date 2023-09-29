package com.bootcamp.repository;

import com.bootcamp.entity.KelasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KelasRepo extends JpaRepository<KelasEntity, String> {
}
