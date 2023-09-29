package com.bootcamp.model;

import com.bootcamp.entity.DosenEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DosenModel {

    private String id;
    private String nip;
    private String name;
    private String jk;
    private String alamat;
    private String gelar;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updateAt;
    private String updateBy;

    public DosenModel(DosenEntity entity){
        BeanUtils.copyProperties(entity, this);
    }
}
