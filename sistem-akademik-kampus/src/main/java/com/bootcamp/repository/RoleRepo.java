package com.bootcamp.repository;

import com.bootcamp.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepo extends JpaRepository<RoleEntity, String> {

//    RoleEntity findByName (String name);
//    @Query("select t from RoleEntity t where t.name in :names")
//    List<RoleEntity> findByNameFetchMenus(@Param("names") List<String> names);
//
//    @Query("select t from RoleEntity t where t.id=:id")
//    RoleEntity findByIdFetchMenuAndPrivilage(@Param("id") String id);
}
