package com.bootcamp.model;


import com.bootcamp.entity.MatakuliahEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatakuliahModel {
    private String id;
    private String code;
    private String name;
    private Integer sks;
    private List<KelasModel> kelas = new ArrayList<>();


    public MatakuliahModel(MatakuliahEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
