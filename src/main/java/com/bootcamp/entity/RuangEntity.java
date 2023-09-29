package com.bootcamp.entity;

import com.bootcamp.model.RuangModel;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ruang")
public class RuangEntity {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name ="code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at", length = 20)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_at",length = 20)
    private LocalDateTime updateAt;

    @Column(name = "update_by")
    private String updateBy;

    public RuangEntity(RuangModel model) {
        BeanUtils.copyProperties(model, this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.updateAt = LocalDateTime.now();
        this.updateBy = "SYSTEM";
    }
}
