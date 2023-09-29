package com.bootcamp.entity;

import com.bootcamp.model.JurusanModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @Column(name = "code")
    private String code;

    @Column(name = "name", length = 225)
    private String name;

    @Column(name = "created_at", length = 20)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_at", length = 20)
    private LocalDateTime updateAt;

    @Column(name = "update_by")
    private String updateBy;

    public JurusanEntity(JurusanModel jurusanModel) {
        BeanUtils.copyProperties(jurusanModel, this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.updateAt = LocalDateTime.now();
        this.updateBy = "SYSTEM";
    }
}
