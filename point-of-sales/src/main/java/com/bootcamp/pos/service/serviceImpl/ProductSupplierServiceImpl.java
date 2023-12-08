package com.bootcamp.pos.service.serviceImpl;

import com.bootcamp.pos.model.entity.ProductSuppliersEntity;
import com.bootcamp.pos.model.request.ProductSupplierRequest;
import com.bootcamp.pos.repository.ProductSupplierRepo;
import com.bootcamp.pos.service.AddressService;
import com.bootcamp.pos.service.ProductSupplierService;
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
public class ProductSupplierServiceImpl implements ProductSupplierService {

    private final ProductSupplierRepo ProductSupplierRepo;
    @Override
    public List<ProductSupplierRequest> getAll() {
        return this.ProductSupplierRepo.findAll().stream().map(ProductSupplierRequest::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional <ProductSupplierRequest> getById(String id) {
        ProductSuppliersEntity result = this.ProductSupplierRepo.findById(id).orElse(null);
        if (result == null){
            return Optional.empty();
        }

        return Optional.of(new ProductSupplierRequest(result));
    }

    @Override
    public Optional<ProductSupplierRequest> save(ProductSupplierRequest request) {
        if (request == null){
            return Optional.empty();
        }

        ProductSuppliersEntity result = new ProductSuppliersEntity(request);

        try {
            this.ProductSupplierRepo.save(result);
            log.info("Save Product Supplier To database Success");
            return Optional.of(new ProductSupplierRequest(result));
        } catch (Exception e){
            log.error("Save Product Supplier to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProductSupplierRequest> update(ProductSupplierRequest request, String id) {
        ProductSuppliersEntity entity = this.ProductSupplierRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        entity.setId(id);

        try {
            this.ProductSupplierRepo.save(entity);
            log.info("Update Product Supplier to database success");
            return Optional.of(new ProductSupplierRequest(entity));
        } catch (Exception e){
            log.error("Update Product Supplier to database failed, error:{}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProductSupplierRequest> delete(String id) {
        ProductSuppliersEntity entity = this.ProductSupplierRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Product Supplier With id :{}, not Found", id);
            return Optional.empty();
        }

        try {
            this.ProductSupplierRepo.delete(entity);
            log.info("Delete Data Product Supplier from database success");
            return Optional.of(new ProductSupplierRequest(entity));
        } catch (Exception e){
            log.info("Delete Data Product Supplier From database failed, error:{}",e.getMessage());
            return Optional.empty();
        }


    }
}
