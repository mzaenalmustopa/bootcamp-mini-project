package com.bootcamp.model;

import com.bootcamp.entity.RuangEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuangModel {
    private String id;
    private String code;
    private String name;
    private String gedungId;
    private String gedungName;
    private List<KelasModel> kelas =  new ArrayList<>();

    public RuangModel(RuangEntity entity) {
        BeanUtils.copyProperties(entity, this);
        if (entity.getGedung() != null){
            gedungId = entity.getGedung().getId();
            gedungName = entity.getGedung().getName();
        }
    }
}
