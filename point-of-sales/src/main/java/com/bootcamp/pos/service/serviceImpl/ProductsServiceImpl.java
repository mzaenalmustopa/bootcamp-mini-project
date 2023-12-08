package com.bootcamp.pos.service.serviceImpl;

import com.bootcamp.pos.model.entity.ProductsEntity;
import com.bootcamp.pos.model.request.ProductsRequest;
import com.bootcamp.pos.repository.ProductRepo;
import com.bootcamp.pos.service.ProductsService;
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
public class ProductsServiceImpl implements ProductsService {

    private final ProductRepo productRepo;
    @Override
    public List<ProductsRequest> getAll() {
        return this.productRepo.findAll().stream().map(ProductsRequest::new).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductsRequest> getById(String id) {
        ProductsEntity entity = this.productRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        return Optional.of(new ProductsRequest(entity));
    }

    @Override
    public Optional<ProductsRequest> save(ProductsRequest request) {
        if (request == null){
            return Optional.empty();
        }

        ProductsEntity result = new ProductsEntity(request);

        try {
            this.productRepo.save(result);
            log.info("Save Products To Database Success");
            return Optional.of(new ProductsRequest(result));
        } catch ( Exception e){
            log.error("Save Products To Database failed, error:{}", e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public Optional<ProductsRequest> update(ProductsRequest request, String id) {
        ProductsEntity entity = this.productRepo.findById(id).orElse(null);
        if (entity == null) {
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        entity.setId(id);

        try {
            this.productRepo.save(entity);
            log.info("Update Products to Database success");
            return Optional.of(new ProductsRequest(entity));
        } catch (Exception e){
            log.error("Update Products to database failed, error:{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProductsRequest> delete(String id) {
        ProductsEntity entity = this.productRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Products with id :{}, not found", id);
            return Optional.empty();
        }


        try {
            this.productRepo.delete(entity);
            log.info("Delete Products from database success");
            return Optional.of(new ProductsRequest(entity));
        } catch (Exception e){
            log.error("Delete products From database failed, error:{}", e.getMessage());
            return Optional.empty();
        }
    }
}
