package com.bootcamp.northwind.service.serviceImpl;

import com.bootcamp.northwind.model.entity.SupplierEntity;
import com.bootcamp.northwind.model.request.SupplierRequest;
import com.bootcamp.northwind.repository.SupplierRepo;
import com.bootcamp.northwind.service.SupplierService;
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
    public Optional<SupplierRequest> getById(Long id) {
        SupplierEntity result = this.supplierRepo.findById(id).orElse(null);
        if (result == null){
            return Optional.empty();
        }

        SupplierRequest request = new SupplierRequest(result);
        return Optional.of(request);
    }

    @Override
    public Optional<SupplierRequest> save(SupplierRequest request) {
        if (request == null){
            return Optional.empty();
        }

        SupplierEntity result = new SupplierEntity(request);
        BeanUtils.copyProperties(request, result);

        try {
            this.supplierRepo.save(result);
            log.info("Save Supplier to database success");
            return Optional.of(new SupplierRequest(result));
        } catch (Exception e){
            log.error("Save Supplier to database failed, error :{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<SupplierRequest> update(SupplierRequest request, Long id) {
        SupplierEntity entity = this.supplierRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);

        try {
            this.supplierRepo.save(entity);
            log.info("Update Supplier to database success");
            return Optional.of(new SupplierRequest(entity));
        } catch (Exception e ){
            log.error("Update Supplier to database failed, error:{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<SupplierRequest> delete(Long id) {
        SupplierEntity entity = this.supplierRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Supplier With id :{}, not found", id);
            return Optional.empty();
        }

        try {
            this.supplierRepo.delete(entity);
            log.info("Delete From Database Supplier Success");
            return Optional.of(new SupplierRequest(entity));
        } catch (Exception e){
            log.error("Delete Supplier From Database Failed, error:{}",e.getMessage());
            return Optional.empty();
        }
    }
}
