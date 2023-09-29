package com.bootcamp.model;

import com.bootcamp.entity.RuangEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuangModel {
    private String id;
    private String code;
    private String name;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updateAt;
    private String updateBy;

    public RuangModel(RuangEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
