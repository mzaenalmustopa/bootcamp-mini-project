package com.bootcamp.repository;

import com.bootcamp.entity.MatakuliahEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatakuliahRepo extends JpaRepository<MatakuliahEntity , String> {
}
