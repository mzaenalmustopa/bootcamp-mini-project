package com.bootcamp.model;

import com.bootcamp.entity.KelasDetailEntity;
import com.bootcamp.entity.KelasEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    private Integer semester;
    private Integer tahunAjaran;
    private Integer kuota;
    private Boolean bisaOnline;
    private String ruangId;
    private String ruangName;
    private String dosenId;
    private String dosenName;
    private String matakuliahId;
    private String matakuliahName;
    private List<KelasDetailModel> kelasDetail = new ArrayList<>();

    public KelasModel(KelasEntity entity) {
        BeanUtils.copyProperties(entity, this);
        if (entity.getRuang() != null){
            ruangId = entity.getRuang().getId();
            ruangName = entity.getRuang().getName();
        }

        if (entity.getDosen() != null){
            dosenId = entity.getDosen().getId();
            dosenName = entity.getDosen().getName();
        }

        if (entity.getMatakuliah() != null){
            matakuliahId = entity.getMatakuliah().getId();
            matakuliahName = entity.getMatakuliah().getName();
        }
    }
}
