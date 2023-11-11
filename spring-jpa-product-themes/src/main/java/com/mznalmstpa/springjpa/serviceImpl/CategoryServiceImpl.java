package com.mznalmstpa.springjpa.serviceImpl;

import com.mznalmstpa.springjpa.entity.CategoryEntity;
import com.mznalmstpa.springjpa.model.CategoryModel;
import com.mznalmstpa.springjpa.repository.CategoryRepo;
import com.mznalmstpa.springjpa.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<CategoryModel> gets() {
        List<CategoryEntity> result = this.categoryRepo.findAll();
        if (result.isEmpty()){
            return Collections.emptyList();
        }

        return result.stream().map(CategoryModel::new).collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryModel> getById(Long id) {
        CategoryEntity entity = this.categoryRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        CategoryModel result = new CategoryModel(entity);
        return Optional.of(result);
    }

    @Override
    public void save(CategoryModel request) {
        // mengecek nama sudah tersedia atau belum
        if (this.categoryRepo.existsByName(request.getName())){
            log.warn("Save Category failed, error, name with {} is exist", request.getName());
            return;
        }
        // proses create object
        CategoryEntity entity = new CategoryEntity();
        // copy value property to entity
        BeanUtils.copyProperties(request, entity);
        // try save
        try {
            this.categoryRepo.save(entity);
            log.info("Save Customer Success");
        }catch (Exception e){
            log.warn("Save Category Failed, error{}",e.getMessage());
        }
    }

    @Override
    public void update(CategoryModel request, Long id) {
        CategoryEntity entity = this.categoryRepo.findById(id).orElse(null);
        if (entity == null){
            return;
        }

        // copy property
        BeanUtils.copyProperties(request, entity);
        entity.setId(id);
        // try save
        try {
            this.categoryRepo.save(entity);
            log.info("Update Category Success");
        }catch (Exception e){
            log.warn("Update Category failed, error{}",e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        CategoryEntity entity = this.categoryRepo.findById(id).orElse(null);
        if (entity == null){
            return;
        }

        try {
            this.categoryRepo.delete(entity);
            log.info("Delete Category Success");
        }catch (Exception e){
            log.warn("Delete category failed, error:{}",e.getMessage());
        }
    }
}
