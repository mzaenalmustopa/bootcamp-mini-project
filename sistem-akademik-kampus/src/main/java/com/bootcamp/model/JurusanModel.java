package com.bootcamp.model;


import com.bootcamp.entity.JurusanEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JurusanModel {
    private String id;
    private String code;
    private String name;
    private String fakultasId;
    private String fakultasName;
    private List<MahasiswaModel> mahasiswa = new ArrayList<>();

    public JurusanModel(JurusanEntity entity) {
        BeanUtils.copyProperties(entity, this);
        if (entity.getFakultas() != null){
            fakultasId = entity.getFakultas().getId();
            fakultasName = entity.getFakultas().getName();
        }
    }
}
