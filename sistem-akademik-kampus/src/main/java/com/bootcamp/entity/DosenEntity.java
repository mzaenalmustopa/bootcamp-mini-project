package com.bootcamp.entity;

import com.bootcamp.model.DosenModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dosen")
public class DosenEntity {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "nip", unique = true)
    private String nip;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "jk")
    private String jk;

    @Column(name = "alamat", length = 200)
    private String alamat;

    @Column(name = "gelar")
    private String gelar;

    @Column(name = "created_at", length = 20)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_at", length = 20)
    private LocalDateTime updateAt;

    @Column(name = "update_by")
    private String updatedBy;

    @OneToMany(mappedBy = "dosen", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KelasEntity> kelas;

    public DosenEntity(DosenModel model) {
        BeanUtils.copyProperties(model, this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy ="SYSTEM";
        this.updateAt = LocalDateTime.now();
        this.updatedBy = "SYSTEM";
    }

    public DosenEntity(String id) {
        this.id = id;
    }
}
