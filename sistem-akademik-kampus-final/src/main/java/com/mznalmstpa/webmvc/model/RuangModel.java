package com.mznalmstpa.webmvc.model;

import com.mznalmstpa.webmvc.entity.RuangEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RuangModel {
    private String id;
    @NotEmpty(message = "*Kode tidak boleh kosong")
    private String code;
    @NotEmpty(message = "*Nama wajib diisi")
    private String name;
    @NotNull(message = "*Lantai-ke tidak boleh kosong")
    private Integer lantaiKe;
    @NotEmpty(message = "*Gedung harus diisi")
    private String gedungId;
    private String gedungName;

    public RuangModel() {
    }

    public RuangModel(String code, String name, String gedungId) {
        this.code = code;
        this.name = name;
        this.gedungId = gedungId;
    }

    public RuangModel(RuangEntity entity) {
        BeanUtils.copyProperties(entity, this);
        if (entity.getGedung() != null) {
            gedungId = entity.getGedung().getId();
            gedungName = entity.getGedung().getName();
        }
    }
}
