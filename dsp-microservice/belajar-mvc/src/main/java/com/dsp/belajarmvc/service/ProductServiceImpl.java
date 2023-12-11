package com.dsp.belajarmvc.service;

import com.dsp.belajarmvc.model.entity.CategoryEntity;
import com.dsp.belajarmvc.model.entity.ProductEntity;
import com.dsp.belajarmvc.model.request.ProductRequest;
import com.dsp.belajarmvc.model.response.CategoryResponse;
import com.dsp.belajarmvc.model.response.ProductResponse;
import com.dsp.belajarmvc.repository.CategoryRepository;
import com.dsp.belajarmvc.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository repository;
    private CategoryRepository categoryRepo;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, CategoryRepository categoryRepo) {
        this.repository = repository;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<CategoryResponse> getCategory() {
        List<CategoryEntity> result = this.categoryRepo.findAll();
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getAll() {
        List<ProductEntity> result = this.repository.findAll();
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductResponse> getById(Long id) {
        if(id == 0L) {
            return Optional.empty();
        }
        Optional<ProductEntity> result = repository.findById(id);
        return result.map(ProductResponse::new);
    }

    @Override
    public Optional<ProductResponse> save(ProductRequest request) {
        if(request == null) {
            return Optional.empty();
        }

        CategoryEntity category = this.categoryRepo.findById(request.getCategoryId()).orElse(null);
        if(category == null){
            return Optional.empty();
        }

        ProductEntity product = new ProductEntity(request, category);
        try{
            this.repository.save(product);
            log.info("Save product success");
            return Optional.of(new ProductResponse(product));
        }catch (Exception e){
            log.error("Save product failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProductResponse> update(ProductRequest request, Long id) {
        if(request == null) {
            return Optional.empty();
        }
        ProductEntity product = this.repository.findById(id).orElse(null);
        if(product == null){
            return Optional.empty();
        }

        CategoryEntity category = this.categoryRepo.findById(request.getCategoryId()).orElse(null);
        if(category == null){
            return Optional.empty();
        }

        try{
            BeanUtils.copyProperties(request, product);
            product.setCategory(category);
            this.repository.save(product);

            log.info("Update product success");
            return Optional.of(new ProductResponse(product));
        }catch (Exception e){
            log.error("Update product failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProductResponse> delete(Long id) {
        if(id == 0L) {
            return Optional.empty();
        }

        ProductEntity product = this.repository.findById(id).orElse(null);
        if(product == null){
            return Optional.empty();
        }

        try{
            this.repository.delete(product);
            log.info("Delete product success");
            return Optional.of(new ProductResponse(product));
        }catch (Exception e){
            log.error("Delete product failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
