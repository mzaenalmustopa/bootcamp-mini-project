package com.bootcamp.entity;

import com.bootcamp.model.KelasDetailModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
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

    @Column(name = "kelas_id", length = 36, insertable = false, updatable = false)
    private String kelasId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kelas_id", nullable = false)
    private KelasEntity kelas;

    @Column(name = "mahasiswa_id", length = 36, insertable = false, updatable = false)
    private String mahasiswaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mahasiswa_id", nullable = false)
    private MahasiswaEntity mahasiswa;

    @Column(name = "status", length = 20)
    private String status;

    public KelasDetailEntity(KelasDetailModel model) {
        this.id = UUID.randomUUID().toString();

        KelasEntity kelas = new KelasEntity();
        kelas.setId(model.getKelas().getId());
        this.kelas = kelas;

        MahasiswaEntity mahasiswa = new MahasiswaEntity();
        mahasiswa.setId(model.getMahasiswa().getId());
        this.mahasiswa = mahasiswa;

        this.status = model.getStatus();
    }
}
