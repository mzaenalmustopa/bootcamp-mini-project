package mzaenalmstpa.eduprobackend.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_ruangan")
@AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "ruangan_"))
})
public class RuanganEntity extends BaseEntity{
    private static final long serailVersionUID = 2353355233364429363L;

    @Id
    @Column(name = "kode_ruang", length = 20, nullable = false)
    private String kode;

    @Column(name = "nama_ruang", length = 50, nullable = false)
    private String name;

    @Column(name = "kapasitas_ruang")
    private Integer kapasitas;

    @Column(name = "kode_gedung", length = 20, nullable = false)
    private String kodeGedung;

    @ManyToOne
    @JoinColumn(name = "gedung", insertable = false, updatable = false)
    private GedungEntity gedungEntity;
}
