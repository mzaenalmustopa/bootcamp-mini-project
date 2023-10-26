package com.bootcamp.entity;

import com.bootcamp.model.KelasDetailModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "kelas_detail_tab")
public class KelasDetailEntity {

    @Id
    @Column(name = "id", length = 36, unique = true)
    private String id;

    @Column(name = "kelas_id", length = 36)
    private String kelasId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kelas_id", insertable = false, updatable = false)
    private KelasEntity kelas;

    @Column(name = "mahasiswa_id", length = 36)
    private String mahasiswaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mahasiswa_id", insertable = false, updatable = false)
    private MahasiswaEntity mahasiswa;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "createdAt", length = 20)
    private LocalDateTime createdAt;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "updateAt", length = 20)
    private LocalDateTime updateAt;

    @Column(name = "updateBy")
    private String updateBy;

    public KelasDetailEntity(KelasDetailModel model) {
        BeanUtils.copyProperties(model, this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
    }
}
