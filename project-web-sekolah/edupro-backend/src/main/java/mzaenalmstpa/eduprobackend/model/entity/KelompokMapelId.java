package mzaenalmstpa.eduprobackend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class KelompokMapelId implements Serializable {

    @Column(name = "id_lembaga", nullable = false)
    private Integer idLembaga;

    @Column(name = "kode_lembaga", length = 10, nullable = false)
    private String kode;
}
