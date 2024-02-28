package com.mznalmstpa.webmvc.model;

import com.mznalmstpa.webmvc.entity.DosenEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class DosenModel {

    private String id;

    @NotBlank
    @NotEmpty
    private String nip;

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String jk;

    @NotBlank
    @NotEmpty
    private String alamat;

    @NotBlank
    @NotEmpty
    private String gelar;

    public DosenModel() {
    }

    public DosenModel(DosenEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
