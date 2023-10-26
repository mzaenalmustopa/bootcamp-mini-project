package com.bootcamp.model;

import com.bootcamp.entity.MahasiswaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MahasiswaModel {

    private String id;
    private String nim;
    private String name;
    private String jk;
    private String alamat;
    private String tmpLahir;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tglLahir;
    private String agama;
    private String jurusanId;
    private String jurusanName;
    private List<KelasDetailModel> kelasDetail = new ArrayList<>();

    public MahasiswaModel(MahasiswaEntity entity) {
        BeanUtils.copyProperties(entity, this);
        if (entity.getJurusan() != null){
            jurusanId =  entity.getJurusan().getId();
            jurusanName = entity.getJurusan().getName();
        }
    }
}
