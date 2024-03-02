package mzaenalmstpa.eduprobackend.repository;

import mzaenalmstpa.eduprobackend.model.entity.LevelEntity;
import mzaenalmstpa.eduprobackend.model.entity.LevelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepo extends JpaRepository<LevelEntity, LevelId> {
}
