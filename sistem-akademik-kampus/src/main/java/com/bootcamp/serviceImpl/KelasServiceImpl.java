package com.bootcamp.serviceImpl;

import com.bootcamp.entity.DosenEntity;
import com.bootcamp.entity.KelasEntity;
import com.bootcamp.entity.MatakuliahEntity;
import com.bootcamp.entity.RuangEntity;
import com.bootcamp.model.KelasModel;
import com.bootcamp.repository.DosenRepo;
import com.bootcamp.repository.KelasRepo;
import com.bootcamp.repository.MatakuliahRepo;
import com.bootcamp.repository.RuangRepo;
import com.bootcamp.service.KelasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class KelasServiceImpl implements KelasService {

    private final KelasRepo kelasRepo;
    private final RuangRepo ruangRepo;
    private final MatakuliahRepo matakuliahRepo;
    private final DosenRepo dosenRepo;


    @Override
    public List<KelasModel> getAll() {
        List<KelasEntity> result = kelasRepo.findAll();
        if(result.isEmpty()){
           return Collections.emptyList();
        }

        return result.stream().map(KelasModel::new).collect(Collectors.toList());
    }

    @Override
    public KelasModel getById(String id) {
        if (id == null){
            return new KelasModel();
        }
        return kelasRepo.findById(id).map(KelasModel::new).orElse(new KelasModel());
    }

    @Override
    public Optional<KelasModel> save(KelasModel request) {

        if (request == null){
            return Optional.empty();
        }

        RuangEntity ruang = this.ruangRepo.findById(request.getRuangId()).orElse(null);
        if (ruang == null){
            return Optional.empty();
        }

        MatakuliahEntity matkul = this.matakuliahRepo.findById(request.getMatakuliahId()).orElse(null);
        if (matkul ==  null){
            return Optional.empty();
        }

        DosenEntity dosen = this.dosenRepo.findById(request.getDosenId()).orElse(null);
        if (dosen == null){
            return Optional.empty();
        }


        KelasEntity entity = new KelasEntity(request);
        try {
            kelasRepo.save(entity);
            log.info("Save Kelas to database successfully ");
            return Optional.of(new KelasModel(entity));
        }catch (Exception e){
            log.error("Save Kelas to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelasModel> update(String id, KelasModel request) {
        KelasEntity entity = this.kelasRepo.findById(id).orElse(null);
            if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        RuangEntity ruang = new RuangEntity(request.getRuangId());
        DosenEntity dosen = new DosenEntity(request.getDosenId());
        MatakuliahEntity matakuliah = new MatakuliahEntity(request.getMatakuliahId());
        entity.setId(id);
        entity.setRuang(ruang);
        entity.setDosen(dosen);
        entity.setMatakuliah(matakuliah);
        entity.setUpdateAt(LocalDateTime.now());

        try {
            this.kelasRepo.save(entity);
            log.info("Update kelas to database successfully");
            return Optional.of(new KelasModel(entity));
        }catch (Exception e){
            log.error("Update kelas to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelasModel> delete(String id) {
        KelasEntity entity = this.kelasRepo.findById(id).orElse(new KelasEntity());
        if (entity == null){
            log.warn("Kelas with id{}, not found", id);
            return Optional.empty();
        }

        try {
            kelasRepo.delete(entity);
            log.info("Delete kelas to database successfully");
            return Optional.empty();
        }catch (Exception e){
            log.error("Delete kelas to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }
}
