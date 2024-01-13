package tugasuasdimasl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import tugasuasdimasl.model.PersonModel;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class AddressEntity {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "street")
    private String street;

    @Column(name = "district")
    private String district;

    @Column(name = "city")
    private String city;

    @Column(name = "province")
    private String province;

    public AddressEntity(PersonModel request) {
        BeanUtils.copyProperties(request , this);
        this.id = UUID.randomUUID().toString();
    }
}
