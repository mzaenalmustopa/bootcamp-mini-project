package com.bootcamp.entity;

import com.bootcamp.model.RuangModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ruang")
public class RuangEntity {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name ="code_ruang", unique = true)
    private String code;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "gedung_id", length = 36, insertable = false, updatable = false)
    private String gedungId;

    @Column(name = "created_at", length = 20)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_at",length = 20)
    private LocalDateTime updateAt;

    @Column(name = "update_by")
    private String updateBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gedung_id", nullable = false)
    private GedungEntity gedung;

    @OneToMany(mappedBy = "ruang", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KelasEntity> kelas;

    public RuangEntity(RuangModel model, GedungEntity gedung) {
        this.id = UUID.randomUUID().toString();
        this.code = model.getCode();
        this.name = model.getName();
        this.gedung = gedung;
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
    }

    public RuangEntity(String id) {
        this.id = id;
    }
}
