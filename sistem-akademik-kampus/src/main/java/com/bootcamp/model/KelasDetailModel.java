package com.bootcamp.model;

import com.bootcamp.entity.KelasDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelasDetailModel {
    private String id;
    private String kelasId;
    private String mahasiswaId;
    private String status;
    private String kelasName;
    private String mahasiswaName;


    public KelasDetailModel(KelasDetailEntity entity) {
        BeanUtils.copyProperties(entity, this);
        if (entity.getKelasId() != null){
            kelasId = entity.getKelas().getId();
            kelasName = entity.getKelas().getCode();
        }

        if (entity.getMahasiswaId() != null){
            mahasiswaId = entity.getMahasiswa().getId();
            mahasiswaName = entity.getMahasiswa().getName();
        }
    }
}
