package com.bootcamp.pos.service.serviceImpl;

import com.bootcamp.pos.model.entity.SuppliersEntity;
import com.bootcamp.pos.model.request.SupplierRequest;
import com.bootcamp.pos.repository.SupplierRepo;
import com.bootcamp.pos.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepo supplierRepo;

    @Override
    public List<SupplierRequest> getAll() {
        return this.supplierRepo.findAll().stream().map(SupplierRequest::new).collect(Collectors.toList());
    }

    @Override
    public Optional<SupplierRequest> getById(String id) {
        SuppliersEntity entity = this.supplierRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        return Optional.of(new SupplierRequest(entity));
    }

    @Override
    public Optional<SupplierRequest> save(SupplierRequest request) {
        if (request == null){
            return Optional.empty();
        }

        SuppliersEntity result = new SuppliersEntity(request);

        try {
            this.supplierRepo.save(result);
            log.info("Save Supplier To Database Success");
            return Optional.of(new SupplierRequest(result));
        } catch (Exception e){
            log.error("Save Supplier to database failed, error:{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<SupplierRequest> update(SupplierRequest request, String id) {
        SuppliersEntity entity = this.supplierRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        entity.setId(id);

        try {
            this.supplierRepo.save(entity);
            log.info("Update Supplier Success");
            return Optional.of(new SupplierRequest(entity));
        } catch (Exception e) {
            log.error("Update Supplier failed, error:{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<SupplierRequest> delete(String id) {
        SuppliersEntity result = this.supplierRepo.findById(id).orElse(null);
        if (result == null){
            log.warn("Supplier With id :{}, Not Found", id);
            return Optional.empty();
        }

        try {
            this.supplierRepo.delete(result);
            log.info("Delete Data Supplier From Database Success");
            return Optional.of(new SupplierRequest(result));
        } catch (Exception e){
            log.error("Delete Data Supplier From Database Failed, error:{}",e.getMessage());
            return Optional.empty();
        }
    }
}
