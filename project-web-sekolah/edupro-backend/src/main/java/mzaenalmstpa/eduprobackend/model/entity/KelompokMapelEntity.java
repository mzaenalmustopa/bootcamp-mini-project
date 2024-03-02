package mzaenalmstpa.eduprobackend.model.entity;

import jakarta.persistence.*;
import lombok.*;
import mzaenalmstpa.eduprobackend.constant.DataStatus;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_klmpok_mapel")
public class KelompokMapelEntity extends BlankBaseEntity{

    @EmbeddedId
    private KelompokMapelId id;

    @Column(name = "nama_klmpok", length = 100, nullable = false)
    private String nama;

    @Builder.Default
    @Column(name = "status_klmpok", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private DataStatus status = DataStatus.AKTIF;
}
