package tugasuasdimasl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugasuasdimasl.entity.PersonEntity;

@Repository
public interface PersonRepo extends JpaRepository<PersonEntity, String> {
}
