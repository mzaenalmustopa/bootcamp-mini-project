package com.bootcamp.model;

import com.bootcamp.entity.DosenEntity;
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
public class DosenModel {

    private String id;
    private String nip;
    private String name;
    private String jk;
    private String alamat;
    private String gelar;
    private List<KelasModel> kelas = new ArrayList<>();

    public DosenModel(DosenEntity entity){
        BeanUtils.copyProperties(entity, this);
    }
}
