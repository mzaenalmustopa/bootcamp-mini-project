package com.bootcamp.model;

import com.bootcamp.entity.KelasEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelasModel {
    private String id;
    private String code;
    private String namaHari;
    @DateTimeFormat(pattern = "HH:mm")
    private Date jamMulai;
    @DateTimeFormat(pattern = "HH:mm")
    private Date jamSelesai;
    private String status;
    private String semester;
    private Integer tahunAjaran;
    private Integer kuota;
    private Boolean bisaOnline;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updateAt;
    private String updateBy;

    public KelasModel(KelasEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
