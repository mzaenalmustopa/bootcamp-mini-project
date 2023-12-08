package com.bootcamp.pos.service.serviceImpl;

import com.bootcamp.pos.model.entity.InventoryLocEntity;
import com.bootcamp.pos.model.request.InventoryLocRequest;
import com.bootcamp.pos.repository.InventoryLocRepo;
import com.bootcamp.pos.service.InventoryLocService;
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
public class InventoryLocServiceImpl implements InventoryLocService {
    private final InventoryLocRepo inventoryLocRepo;

    @Override
    public List<InventoryLocRequest> getAll() {
        return this.inventoryLocRepo.findAll().stream().map(InventoryLocRequest::new).collect(Collectors.toList());
    }

    @Override
    public Optional<InventoryLocRequest> getById(String id) {
        InventoryLocEntity entity = this.inventoryLocRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        return Optional.of(new InventoryLocRequest(entity));
    }

    @Override
    public Optional<InventoryLocRequest> save(InventoryLocRequest request) {
        if (request == null){
            return Optional.empty();
        }

        InventoryLocEntity result = new InventoryLocEntity(request);

        try {
            this.inventoryLocRepo.save(result);
            log.info("Save Inventory Loc To Database Success");
            return Optional.of(new InventoryLocRequest(result));
        } catch (Exception e){
            log.error("Save Inventory Loc to database failed, error:{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<InventoryLocRequest> update(InventoryLocRequest request, String id) {
        InventoryLocEntity entity = this.inventoryLocRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        entity.setId(id);

        try {
            this.inventoryLocRepo.save(entity);
            log.info("Update Inventory Loc Success");
            return Optional.of(new InventoryLocRequest(entity));
        } catch (Exception e) {
            log.error("Update Inventory Loc failed, error:{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<InventoryLocRequest> delete(String id) {
        InventoryLocEntity result = this.inventoryLocRepo.findById(id).orElse(null);
        if (result == null){
            log.warn("Inventory Loc With id :{}, Not Found", id);
            return Optional.empty();
        }

        try {
            this.inventoryLocRepo.delete(result);
            log.info("Delete Data Inventory Loc From Database Success");
            return Optional.of(new InventoryLocRequest(result));
        } catch (Exception e){
            log.error("Delete Data Inventory Loc From Database Failed, error:{}",e.getMessage());
            return Optional.empty();
        }
    }
}
