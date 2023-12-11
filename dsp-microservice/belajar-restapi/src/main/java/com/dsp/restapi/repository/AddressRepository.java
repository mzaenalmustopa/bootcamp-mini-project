package com.dsp.restapi.repository;

import com.dsp.restapi.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String> {
    @Query("select t from AddressEntity t where t.id = :id and t.contact.id = :contactId")
    Optional<AddressEntity> findByIdAndContactId(@Param("id") String id,
                                                 @Param("contactId") String contactId);
}
