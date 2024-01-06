package tugasuasdimasl.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tugasuasdimasl.entity.PersonEntity;
import tugasuasdimasl.model.PersonModel;
import tugasuasdimasl.repository.PersonRepo;
import tugasuasdimasl.service.PersonService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepo personRepo;
    @Override
    public List<PersonModel> getAll() {
        return this.personRepo.findAll().stream().map(PersonModel::new).collect(Collectors.toList());
    }

    @Override
    public Optional<PersonModel> getById(String id) {
        if (id == null){
            return Optional.empty();
        }

        return Optional.of(this.personRepo.findById(id).map(PersonModel::new).orElse(new PersonModel()));
    }

    @Override
    public Optional<PersonModel> save(PersonModel request) {
        if (request == null){
            return Optional.empty();
        }

        PersonEntity result = new PersonEntity(request);
        try {
            this.personRepo.save(result);
            log.info("Save Person to database success");
            return Optional.of(new PersonModel(result));
        }catch (Exception e){
            log.error("Save peson to database failed: {}, error", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<PersonModel> delete(String id) {
        PersonEntity entity = personRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Person with id :{} not found", id);
            return Optional.empty();
        }

        try {
            personRepo.delete(entity);
            log.info("Delete data person from database success");
            return Optional.of(new PersonModel(entity));
        } catch (Exception e){
            log.error("Delete Dosen to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }
}
