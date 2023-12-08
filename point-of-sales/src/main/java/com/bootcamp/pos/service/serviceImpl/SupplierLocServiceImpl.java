package com.bootcamp.pos.service.serviceImpl;

import com.bootcamp.pos.model.entity.SupplierLocEntity;
import com.bootcamp.pos.model.request.SupplierLocRequest;
import com.bootcamp.pos.repository.AddressRepo;
import com.bootcamp.pos.repository.SupplierLocRepo;
import com.bootcamp.pos.service.AddressService;
import com.bootcamp.pos.service.SupplierLocService;
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
public class SupplierLocServiceImpl implements SupplierLocService {

    private final SupplierLocRepo supplierLocRepo;
    @Override
    public List<SupplierLocRequest> getAll() {
        return this.supplierLocRepo.findAll().stream().map(SupplierLocRequest::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional <SupplierLocRequest> getById(String id) {
        SupplierLocEntity result = this.supplierLocRepo.findById(id).orElse(null);
        if (result == null){
            return Optional.empty();
        }

        return Optional.of(new SupplierLocRequest(result));
    }

    @Override
    public Optional<SupplierLocRequest> save(SupplierLocRequest request) {
        if (request == null){
            return Optional.empty();
        }

        SupplierLocEntity result = new SupplierLocEntity(request);

        try {
            this.supplierLocRepo.save(result);
            log.info("Save Supplier Loc To database Success");
            return Optional.of(new SupplierLocRequest(result));
        } catch (Exception e){
            log.error("Save Supplier Loc to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<SupplierLocRequest> update(SupplierLocRequest request, String id) {
        SupplierLocEntity entity = this.supplierLocRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        entity.setId(id);

        try {
            this.supplierLocRepo.save(entity);
            log.info("Update Supplier Loc to database success");
            return Optional.of(new SupplierLocRequest(entity));
        } catch (Exception e){
            log.error("Update Supplier Loc to database failed, error:{}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<SupplierLocRequest> delete(String id) {
        SupplierLocEntity entity = this.supplierLocRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Supplier Loc With id :{}, not Found", id);
            return Optional.empty();
        }

        try {
            this.supplierLocRepo.delete(entity);
            log.info("Delete Data Supplier Loc from database success");
            return Optional.of(new SupplierLocRequest(entity));
        } catch (Exception e){
            log.info("Delete Data Supplier Loc From database failed, error:{}",e.getMessage());
            return Optional.empty();
        }


    }
}
