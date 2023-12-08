package com.bootcamp.pos.repository;

import com.bootcamp.pos.model.entity.ShoppingBasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingBasketRepo extends JpaRepository<ShoppingBasketEntity, String> {
}
