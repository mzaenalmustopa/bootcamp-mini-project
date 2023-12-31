package tugasuasdimasl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import tugasuasdimasl.entity.PersonEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonModel {

    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;

    public PersonModel(PersonEntity result) {
        BeanUtils.copyProperties(result, this);
    }
}
