package com.mznalmstpa.springjpa.repository;

import com.mznalmstpa.springjpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<UserEntity> findByUsernameOrEmail(String username , String email);
    boolean existsByUsername(String username);
}
