package com.bootcamp.model;

import com.bootcamp.entity.KelasDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelasDetailModel {
    private String id;
    private String kelasId;
    private String mahasiswaId;
    private String status;
    private KelasModel kelas;
    private MahasiswaModel mahasiswa;
    public KelasDetailModel(KelasDetailEntity entity) {
        BeanUtils.copyProperties(entity, this);
        if (entity.getKelasId() != null){
            kelasId = entity.getKelas().getId();
            kelas = new KelasModel(entity.getKelas());
        }

        if (entity.getMahasiswaId() != null){
            mahasiswaId = entity.getMahasiswa().getId();
            mahasiswa = new MahasiswaModel(entity.getMahasiswa());
        }
    }
}
