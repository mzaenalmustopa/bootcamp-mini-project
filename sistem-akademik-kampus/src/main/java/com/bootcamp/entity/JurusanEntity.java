package com.bootcamp.entity;

import com.bootcamp.model.JurusanModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jurusan")
public class JurusanEntity {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "code", length = 20, unique = true)
    private String code;

    @Column(name = "name", length = 225)
    private String name;

    @Column(name = "fakultas_id", length = 36, insertable = false , updatable = false)
    private String fakultasId;

    @Column(name = "created_at", length = 20)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_at", length = 20)
    private LocalDateTime updateAt;

    @Column(name = "update_by")
    private String updateBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fakultas_id", nullable = false)
    private FakultasEntity fakultas;

    public JurusanEntity(JurusanModel model, FakultasEntity fakultas) {
        this.id = UUID.randomUUID().toString();
        this.code = model.getCode();
        this.name = model.getName();
        this.fakultas = fakultas;
        this.createdAt=LocalDateTime.now();
        this.createdBy="SYSTEM";

    }

    public JurusanEntity(String id) {
        this.id = id;
    }
}
