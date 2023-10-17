package com.bootcamp.entity;

import com.bootcamp.model.MahasiswaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "mahasiswa")
public class MahasiswaEntity {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "jk", length = 36)
    private String jk;

    @Column(name = "alamat", length = 200)
    private String alamat;

    @Column(name = "tmpLahir", length = 100)
    private String tmpLahir;

    @Column(name = "tglLahir")
    private LocalDate tglLahir;

    @Column(name = "agama", length = 36)
    private String agama;

    @Column(name = "jurusan_id", length = 36)
    private String jurusanId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jurusan_id", insertable = false, updatable = false )
    private JurusanEntity jurusan;

    @Column(name = "created_at", length = 20)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_at", length = 20)
    private LocalDateTime updateAt;

    @Column(name = "update_by")
    private String updateBy;

    @OneToMany(mappedBy = "mahasiswa", fetch = FetchType.EAGER)
    private List<KelasDetailEntity> kelasDetail;

    public MahasiswaEntity(MahasiswaModel model) {
        BeanUtils.copyProperties(model, this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy ="SYSTEM";
        this.updateAt = LocalDateTime.now();
        this.updateBy ="SYSTEM";
    }

    public MahasiswaEntity(String id) {
        this.id = id;
    }
}
