package com.bootcamp.pos.repository;

import com.bootcamp.pos.model.entity.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepo extends JpaRepository<PaymentMethodEntity, String> {
}
