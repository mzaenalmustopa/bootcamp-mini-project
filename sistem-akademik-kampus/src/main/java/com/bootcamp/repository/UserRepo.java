package com.bootcamp.repository;

import com.bootcamp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {
   // List<UserEntity> findByUsernameContaining(String keyword);
}
