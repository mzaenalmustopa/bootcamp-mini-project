package tugasuasdimasl.service;

import tugasuasdimasl.model.PersonModel;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<PersonModel> getAll();
    Optional<PersonModel> getById(String id);
    Optional<PersonModel> save (PersonModel request);
    Optional<PersonModel> delete(String id);
}
