package com.bootcamp.model;

import com.bootcamp.entity.MahasiswaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MahasiswaModel {

    private String id;
    private String name;
    private String jk;
    private String alamat;
    private String tmpLahir;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tglLahir;
    private String agama;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updateAt;
    private String updateBy;

    public MahasiswaModel(MahasiswaEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
