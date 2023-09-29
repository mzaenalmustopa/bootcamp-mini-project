package com.bootcamp.entity;

import com.bootcamp.model.MahasiswaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column(name = "jk")
    private String jk;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "tmpLahir")
    private String tmpLahir;

    @Column(name = "tglLahir")
    private LocalDate tglLahir;

    @Column(name = "agama")
    private String agama;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "update_by")
    private String updateBy;

    public MahasiswaEntity(MahasiswaModel model) {
        BeanUtils.copyProperties(model, this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy ="SYSTEM";
        this.updateAt = LocalDateTime.now();
        this.updateBy ="SYSTEM";
    }
}
