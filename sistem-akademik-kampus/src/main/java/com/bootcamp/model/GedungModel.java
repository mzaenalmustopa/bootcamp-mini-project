package com.bootcamp.model;

import com.bootcamp.entity.GedungEntity;
import com.bootcamp.entity.RuangEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GedungModel {
    private String id;
    private String code;
    private String name;
    private Integer jmlLantai;
    private List<RuangModel> ruangs = new ArrayList<>();

    public GedungModel(GedungEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }

}
