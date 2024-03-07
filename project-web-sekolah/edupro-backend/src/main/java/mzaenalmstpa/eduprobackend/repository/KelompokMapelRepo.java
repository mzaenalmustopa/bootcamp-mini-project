package mzaenalmstpa.eduprobackend.repository;

import mzaenalmstpa.eduprobackend.constant.DataStatus;
import mzaenalmstpa.eduprobackend.model.entity.KelompokMapelEntity;
import mzaenalmstpa.eduprobackend.model.entity.KelompokMapelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KelompokMapelRepo extends JpaRepository<KelompokMapelEntity, KelompokMapelId> {
    List<KelompokMapelEntity> findAllByStatus(DataStatus status);
    boolean existsById(KelompokMapelId id);
}
