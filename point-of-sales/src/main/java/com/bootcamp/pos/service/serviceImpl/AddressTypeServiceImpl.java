package com.bootcamp.pos.service.serviceImpl;

import com.bootcamp.pos.model.entity.AddressTypeEntity;
import com.bootcamp.pos.model.request.AddressTypeRequest;
import com.bootcamp.pos.repository.AddressTypeRepo;
import com.bootcamp.pos.service.AddressTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressTypeServiceImpl implements AddressTypeService {

    private final AddressTypeRepo AddressTypeRepo;
    @Override
    public List<AddressTypeRequest> getAll() {
        return this.AddressTypeRepo.findAll().stream().map(AddressTypeRequest::new)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean validAddressCode(AddressTypeRequest request) {
        List<AddressTypeEntity> checkCode = this.AddressTypeRepo.findByAddressCodeType(request.getAddressCodeType());
        return checkCode.isEmpty();
    }

    @Override
    public Boolean validAddressDesc(AddressTypeRequest request) {
        List<AddressTypeEntity> checkDesc = this.AddressTypeRepo.findByAddressDescType(request.getAddressDescType());
        return checkDesc.isEmpty();
    }

    @Override
    public Optional <AddressTypeRequest> getById(String id) {
        AddressTypeEntity result = this.AddressTypeRepo.findById(id).orElse(null);
        if (result == null){
            return Optional.empty();
        }

        return Optional.of(new AddressTypeRequest(result));
    }

    @Override
    public Optional<AddressTypeRequest> save(AddressTypeRequest request) {
        if (request == null){
            return Optional.empty();
        }

        AddressTypeEntity result = new AddressTypeEntity(request);

        try {
            this.AddressTypeRepo.save(result);
            log.info("Save Address Type To database Success");
            return Optional.of(new AddressTypeRequest(result));
        } catch (Exception e){
            log.error("Save Address Type to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<AddressTypeRequest> update(AddressTypeRequest request, String id) {
        AddressTypeEntity entity = this.AddressTypeRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        entity.setId(id);

        try {
            this.AddressTypeRepo.save(entity);
            log.info("Update Address Type to database success");
            return Optional.of(new AddressTypeRequest(entity));
        } catch (Exception e){
            log.error("Update Address Type to database failed, error:{}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<AddressTypeRequest> delete(String id) {
        AddressTypeEntity entity = this.AddressTypeRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Address Type With id :{}, not Found", id);
            return Optional.empty();
        }

        try {
            this.AddressTypeRepo.delete(entity);
            log.info("Delete Address Type from database success");
            return Optional.of(new AddressTypeRequest(entity));
        } catch (Exception e){
            log.info("Delete Address Type From database failed, error:{}",e.getMessage());
            return Optional.empty();
        }
    }
}
