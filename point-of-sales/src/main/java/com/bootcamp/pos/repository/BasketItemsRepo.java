package com.bootcamp.pos.repository;

import com.bootcamp.pos.model.entity.BasketItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketItemsRepo extends JpaRepository<BasketItemsEntity, String> {
}
