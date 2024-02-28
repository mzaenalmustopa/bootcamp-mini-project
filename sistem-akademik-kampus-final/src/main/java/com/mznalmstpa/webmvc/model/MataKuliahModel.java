package com.mznalmstpa.webmvc.model;

import com.mznalmstpa.webmvc.entity.MataKuliahEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MataKuliahModel {
    private String id;
    @NotEmpty(message = "*Code tidak boleh kosong")
    private String code;
    @NotEmpty(message = "*Nama wajib diisi")
    private String name;
    @NotNull(message = "*Sks tidak boleh kosong")
    private Integer sks;

    public MataKuliahModel() {
    }

    public MataKuliahModel(MataKuliahEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
