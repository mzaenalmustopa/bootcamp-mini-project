package com.bootcamp.pos.repository;

import com.bootcamp.pos.model.entity.InventoryLocEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryLocRepo extends JpaRepository<InventoryLocEntity, String> {
}
