package com.bootcamp.pos.service.serviceImpl;

import com.bootcamp.pos.model.entity.ShoppingBasketEntity;
import com.bootcamp.pos.model.request.ShoppingBasketRequest;
import com.bootcamp.pos.repository.ProductRepo;
import com.bootcamp.pos.repository.ShoppingBasketRepo;
import com.bootcamp.pos.service.ProductsService;
import com.bootcamp.pos.service.ShoppingBasketService;
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
public class ShoppingBasketServiceImpl implements ShoppingBasketService {

    private final ShoppingBasketRepo shoppingBasketRepo;
    @Override
    public List<ShoppingBasketRequest> getAll() {
        return this.shoppingBasketRepo.findAll().stream().map(ShoppingBasketRequest::new).collect(Collectors.toList());
    }

    @Override
    public Optional<ShoppingBasketRequest> getById(String id) {
        ShoppingBasketEntity entity = this.shoppingBasketRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        return Optional.of(new ShoppingBasketRequest(entity));
    }

    @Override
    public Optional<ShoppingBasketRequest> save(ShoppingBasketRequest request) {
        if (request == null){
            return Optional.empty();
        }

        ShoppingBasketEntity result = new ShoppingBasketEntity(request);

        try {
            this.shoppingBasketRepo.save(result);
            log.info("Save Shopping Basket To Database Success");
            return Optional.of(new ShoppingBasketRequest(result));
        } catch ( Exception e){
            log.error("Save Shopping Basket To Database failed, error:{}", e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public Optional<ShoppingBasketRequest> update(ShoppingBasketRequest request, String id) {
        ShoppingBasketEntity entity = this.shoppingBasketRepo.findById(id).orElse(null);
        if (entity == null) {
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        entity.setId(id);

        try {
            this.shoppingBasketRepo.save(entity);
            log.info("Update Shopping Basket to Database success");
            return Optional.of(new ShoppingBasketRequest(entity));
        } catch (Exception e){
            log.error("Update Shopping Basket to database failed, error:{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<ShoppingBasketRequest> delete(String id) {
        ShoppingBasketEntity entity = this.shoppingBasketRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Shopping Basket with id :{}, not found", id);
            return Optional.empty();
        }


        try {
            this.shoppingBasketRepo.delete(entity);
            log.info("Delete Data Shopping Basket from database success");
            return Optional.of(new ShoppingBasketRequest(entity));
        } catch (Exception e){
            log.error("Delete Data Shopping Basket From database failed, error:{}", e.getMessage());
            return Optional.empty();
        }
    }
}
