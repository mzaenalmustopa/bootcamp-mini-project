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
@Table(name = "tbl_level")
public class LevelEntity extends BlankBaseEntity {

    @EmbeddedId
    private LevelId id;

    @Column(name = "level_nama", length = 10, nullable = false)
    private String nama;

    @Builder.Default
    @Column(name = "lvl_status", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private DataStatus status = DataStatus.AKTIF;
}
