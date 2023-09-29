package com.bootcamp.entity;

import com.bootcamp.model.DosenModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
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

    @Column(name = "nip")
    private String nip;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "jk")
    private String jk;

    @Column(name = "alamat")
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

    public DosenEntity(DosenModel model) {
        BeanUtils.copyProperties(model, this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy ="SYSTEM";
        this.updateAt = LocalDateTime.now();
        this.updatedBy = "SYSTEM";
    }
}
