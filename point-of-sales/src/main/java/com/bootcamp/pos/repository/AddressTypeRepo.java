package com.bootcamp.pos.repository;

import com.bootcamp.pos.model.entity.AddressTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressTypeRepo extends JpaRepository<AddressTypeEntity, String> {
    List<AddressTypeEntity> findByAddressCodeType(String addressCodeType);
    List<AddressTypeEntity> findByAddressDescType (String addressDescType);
    List<AddressTypeEntity> findByAddressCodeTypeAndAddressDescType(String addressCodeType, String addressDescType );
}
