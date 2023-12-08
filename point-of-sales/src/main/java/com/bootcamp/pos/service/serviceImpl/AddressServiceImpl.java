package com.bootcamp.pos.service.serviceImpl;

import com.bootcamp.pos.model.entity.AddressEntity;
import com.bootcamp.pos.model.request.AddressRequest;
import com.bootcamp.pos.repository.AddressRepo;
import com.bootcamp.pos.service.AddressService;
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
public class AddressServiceImpl implements AddressService {

    private final AddressRepo AddressRepo;
    @Override
    public List<AddressRequest> getAll() {
        return this.AddressRepo.findAll().stream().map(AddressRequest::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional <AddressRequest> getById(String id) {
        AddressEntity result = this.AddressRepo.findById(id).orElse(null);
        if (result == null){
            return Optional.empty();
        }

        return Optional.of(new AddressRequest(result));
    }

    @Override
    public Optional<AddressRequest> save(AddressRequest request) {
        if (request == null){
            return Optional.empty();
        }

        AddressEntity result = new AddressEntity(request);

        try {
            this.AddressRepo.save(result);
            log.info("Save Address To database Success");
            return Optional.of(new AddressRequest(result));
        } catch (Exception e){
            log.error("Save Address to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<AddressRequest> update(AddressRequest request, String id) {
        AddressEntity entity = this.AddressRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        entity.setId(id);

        try {
            this.AddressRepo.save(entity);
            log.info("Update Address to database success");
            return Optional.of(new AddressRequest(entity));
        } catch (Exception e){
            log.error("Update Address to database failed, error:{}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<AddressRequest> delete(String id) {
        AddressEntity entity = this.AddressRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Address With id :{}, not Found", id);
            return Optional.empty();
        }

        try {
            this.AddressRepo.delete(entity);
            log.info("Delete Address from database success");
            return Optional.of(new AddressRequest(entity));
        } catch (Exception e){
            log.info("Delete Address From database failed, error:{}",e.getMessage());
            return Optional.empty();
        }


    }
}
