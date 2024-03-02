package mzaenalmstpa.eduprobackend.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_gedung")
@AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "created_by")),
        @AttributeOverride(name = "updateAt", column = @Column(name = "update_at")),
        @AttributeOverride(name = "updateBy", column = @Column(name = "update_by")),
        @AttributeOverride(name = "status", column = @Column(name = "status_gdg"))
})
public class GedungEntity extends BaseEntity {
    @Id
    @Column(name = "kode_gd", length = 20, nullable = false)
    private String kode;

    @Column(name = "nama_gd", length = 50, nullable = false)
    private String nama;
}
