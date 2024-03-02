package mzaenalmstpa.eduprobackend.repository;

import mzaenalmstpa.eduprobackend.constant.DataStatus;
import mzaenalmstpa.eduprobackend.model.entity.GedungEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GedungRepo extends JpaRepository<GedungEntity, String> {
    List<GedungEntity> findAllByStatus(DataStatus status);
    boolean existsByKode(String kode);
}
