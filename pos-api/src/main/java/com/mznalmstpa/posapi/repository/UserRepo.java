package com.mznalmstpa.posapi.repository;

import com.mznalmstpa.posapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
//    list dari user entity
    Optional<UserEntity> findByEmail(String email);
}
