package com.bootcamp.repository;

import com.bootcamp.entity.LookUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LookupRepo extends JpaRepository<LookUpEntity, String> {
    List<LookUpEntity> findByGroups(String groups);
    Optional<LookUpEntity> findByCode(String code);
}
