package com.bootcamp.model;


import com.bootcamp.entity.FakultasEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FakultasModel {
    private String id;
    private String code;
    private String name;
    private String alamat;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updateAt;
    private String updateBy;

    public FakultasModel(FakultasEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}

