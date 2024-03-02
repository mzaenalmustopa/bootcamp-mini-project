package mzaenalmstpa.eduprobackend.repository;

import mzaenalmstpa.eduprobackend.model.entity.KelompokMapelEntity;
import mzaenalmstpa.eduprobackend.model.entity.KelompokMapelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KelompokMapelRepo extends JpaRepository<KelompokMapelEntity, KelompokMapelId> {
}
