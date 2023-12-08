package com.app.sikolam.respository;

import com.app.sikolam.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity,Long> {
    Optional<RoleEntity> findByName(String name);
}
