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
public class LevelId implements Serializable {

    @Column(name = "id_lembaga", nullable = false)
    private Integer idLembaga;

    @Column(name = "lvl_kode", length = 10, nullable = false)
    private String kode;
}
