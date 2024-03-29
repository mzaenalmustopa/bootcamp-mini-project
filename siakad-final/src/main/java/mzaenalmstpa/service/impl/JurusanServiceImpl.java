package mzaenalmstpa.service.impl;

import mzaenalmstpa.entity.FakultasEntity;
import mzaenalmstpa.entity.JurusanEntity;
import mzaenalmstpa.model.JurusanModel;
import mzaenalmstpa.repository.JurusanRepository;
import mzaenalmstpa.service.JurusanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JurusanServiceImpl implements JurusanService {
    private JurusanRepository repository;

    @Autowired
    public JurusanServiceImpl(JurusanRepository repository){
        this.repository = repository;
    }

    @Override
    public List<JurusanModel> get() {
        return this.repository.findAll().stream().map(JurusanModel::new).collect(Collectors.toList());
    }

    @Override
    public Boolean validCode(JurusanModel request) {
        List<JurusanEntity> checkCode = this.repository.findByCode(request.getCode());
        return checkCode.isEmpty();
    }

    @Override
    public Boolean validName(JurusanModel request) {
        List<JurusanEntity> checkName = this.repository.findByCode(request.getName());
        return checkName.isEmpty();
    }

    @Override
    public JurusanModel getById(String id) {
        return this.repository.findById(id).map(JurusanModel::new).orElse(new JurusanModel());
    }

    @Override
    public Optional<JurusanModel> save(JurusanModel request) {
        if (request == null){
            return Optional.empty();
        }

        List<JurusanEntity> checkCode = this.repository.findByCode(request.getCode());
        if (!checkCode.isEmpty()){
            return Optional.empty();
        }

        List<JurusanEntity> checkName = this.repository.findByCode(request.getName());
        if (!checkName.isEmpty()){
            return Optional.empty();
        }

        JurusanEntity result = new JurusanEntity(request);
        try {
            this.repository.save(result);
            return Optional.of(new JurusanModel(result));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<JurusanModel> update(String id, JurusanModel request) {
        Optional<JurusanEntity> result = this.repository.findById(id);
        if (result.isEmpty()){
            return Optional.empty();
        }
        JurusanEntity data = result.get();
        data.setCode((request.getCode()));
        data.setName(request.getName());
        FakultasEntity fakultas = new FakultasEntity(request.getFakultasId());
        data.setFakultas(fakultas);

        try {
            this.repository.save(data);
            return Optional.of(new JurusanModel(data));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<JurusanModel> delete(String id) {
        Optional<JurusanEntity> result = this.repository.findById(id);
        if (result.isEmpty()){
            return Optional.empty();
        }

        try {
            JurusanEntity data = result.get();
            FakultasEntity fakultas = data.getFakultas();
            fakultas.removeJurusan(data);
            data.setFakultas(null);
            this.repository.delete(data);
            return Optional.of(new JurusanModel(data));
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
