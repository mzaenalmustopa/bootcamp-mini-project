package com.app.sikolam.respository;

import com.app.sikolam.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author : Roni Purwanto
 * @since : 01/05/2021
 **/
@Repository
public interface MenuRepo extends JpaRepository<MenuEntity,Long> {
    @Query("select t from MenuEntity t where t.name in :names")
    List<MenuEntity> findByNames(@Param("names") List<String> names);

    @Query("select t from MenuEntity t where t.name = :name")
    Optional<MenuEntity> findByName(@Param("name") String name);

    @Query("select t from MenuEntity t where t.parentId = :id")
    List<MenuEntity> findByParentId(@Param("id") String id);
}
