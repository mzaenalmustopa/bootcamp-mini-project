package com.dsp.belajarmvc.service;

import com.dsp.belajarmvc.model.entity.CategoryEntity;
import com.dsp.belajarmvc.model.request.CategoryRequest;
import com.dsp.belajarmvc.model.response.CategoryResponse;
import com.dsp.belajarmvc.repository.CategoryRepository;
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
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<CategoryEntity> result = repository.findAll();
        if(result.isEmpty()){
            return Collections.emptyList();
        }

        return result.stream().map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryResponse> getById(Long id) {
        if(id == 0L) {
            return Optional.empty();
        }

        CategoryEntity result = repository.findById(id).orElse(null);
        if(result == null){
            return Optional.empty();
        }

        return Optional.of(new CategoryResponse(result));
    }

    @Override
    public Optional<CategoryResponse> save(CategoryRequest request) {
        if(request == null) {
            return Optional.empty();
        }

        try{
            CategoryEntity entity = new CategoryEntity();
            BeanUtils.copyProperties(request, entity);
            repository.save(entity);
            log.info("Save category success");
            return Optional.of(new CategoryResponse(entity));
        }catch (Exception e){
            log.error("Save category failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CategoryResponse> update(CategoryRequest request, Long id) {
        if(request == null) {
            return Optional.empty();
        }

        CategoryEntity entity = repository.findById(id).orElse(null);
        if(entity == null){
            return Optional.empty();
        }

        try{
            BeanUtils.copyProperties(request, entity);
            repository.save(entity);
            log.info("Update category success");
            return Optional.of(new CategoryResponse(entity));
        }catch (Exception e){
            log.error("Update category failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CategoryResponse> delete(Long id) {
        if(id == 0L){
            return Optional.empty();
        }
        CategoryEntity entity = repository.findById(id).orElse(null);
        if(entity == null){
            return Optional.empty();
        }

        try{
            repository.delete(entity);
            log.info("Delete category success");
            return Optional.of(new CategoryResponse(entity));
        }catch (Exception e){
            log.error("Delete category failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
