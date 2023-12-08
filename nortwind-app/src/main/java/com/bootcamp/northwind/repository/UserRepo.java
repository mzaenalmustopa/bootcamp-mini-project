package com.bootcamp.northwind.repository;

import com.bootcamp.northwind.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String > {
    Optional<UserEntity> findByUserName(String userName);
    Boolean existsByEmail(String email);
    Optional<UserEntity> findByUserNameOrEmail(String userName, String email);
    boolean existsByUserName(String userName);}
