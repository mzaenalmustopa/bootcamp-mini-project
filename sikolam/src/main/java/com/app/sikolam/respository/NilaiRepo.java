package com.app.sikolam.respository;

import com.app.sikolam.entity.NilaiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NilaiRepo extends JpaRepository<NilaiEntity, String> {
    List<NilaiEntity> findByCategory(String category);
}
