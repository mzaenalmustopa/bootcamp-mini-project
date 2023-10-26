package com.bootcamp.entity;

import com.bootcamp.model.KelasModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "kelas")
public class KelasEntity {

    @Id
    @Column(name = "id", length = 36, unique = true)
    private String id;

    @Column(name = "code",length = 20, unique = true)
    private String code;

    @Column(name = "namaHari")
    private String namaHari;

    @Temporal(TemporalType.TIME)
    @Column(name = "jamMulai")
    private Date jamMulai;

    @Temporal(TemporalType.TIME)
    @Column(name = "jamSelesai")
    private Date jamSelesai;

    @Column(name = "ruang_id", length = 36)
    private String ruangId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ruang_id", insertable = false, updatable = false)
    private RuangEntity ruang;

    @Column(name = "dosen_id", length = 36)
    private String dosenId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dosen_id", insertable = false, updatable = false)
    private DosenEntity dosen;

    @Column(name = "matakuliah_id", length = 36)
    private String matakuliahId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "matakuliah_id", insertable = false, updatable = false)
    private MatakuliahEntity matakuliah;

    @Column(name = "status")
    private String status;

    @Column(name = "semester")
    private String semester;

    @Column(name = "tahunAjaran")
    private Integer tahunAjaran;

    @Column(name = "kuota")
    private Integer kuota;

    @Column(name = "bisaOnline")
    private Boolean bisaOnline;

    @Column(name = "createdAt", length = 20)
    private LocalDateTime createdAt;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "updateAt", length = 20)
    private LocalDateTime updateAt;

    @Column(name = "updateBy")
    private String updateBy;

    @OneToMany(mappedBy = "kelas", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KelasDetailEntity> kelasDetail;

    public KelasEntity(KelasModel model) {
        BeanUtils.copyProperties(model, this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";

    }

    public KelasEntity(String id) {
        this.id = id;
    }
}
