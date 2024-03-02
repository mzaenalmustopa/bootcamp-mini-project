package mzaenalmstpa.eduprobackend.repository;

import mzaenalmstpa.eduprobackend.model.entity.RuanganEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuanganRepo extends JpaRepository<RuanganEntity, String> {
}
