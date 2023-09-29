package com.bootcamp.model;


import com.bootcamp.entity.JurusanEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JurusanModel {
    private String id;
    private String code;
    private String name;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updateAt;
    private String updateBy;

    public JurusanModel(JurusanEntity jurusanEntity) {
        BeanUtils.copyProperties(jurusanEntity, this);
    }
}
