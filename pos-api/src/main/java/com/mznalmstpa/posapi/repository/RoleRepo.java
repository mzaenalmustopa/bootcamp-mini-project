package com.mznalmstpa.posapi.repository;

import com.mznalmstpa.posapi.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    //list property dari role entity
    Optional<RoleEntity> findByName(String roleName);
}