package com.bootcamp.pos.service.serviceImpl;

import com.bootcamp.pos.model.entity.BasketItemsEntity;
import com.bootcamp.pos.model.request.BasketItemsRequest;
import com.bootcamp.pos.repository.BasketItemsRepo;
import com.bootcamp.pos.service.BasketItemService;
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
public class BasketItemServiceImpl implements BasketItemService {

    private final BasketItemsRepo basketItemsRepo;
    @Override
    public List<BasketItemsRequest> getAll() {
        return this.basketItemsRepo.findAll().stream().map(BasketItemsRequest::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional <BasketItemsRequest> getById(String id) {
        BasketItemsEntity result = this.basketItemsRepo.findById(id).orElse(null);
        if (result == null){
            return Optional.empty();
        }

        return Optional.of(new BasketItemsRequest(result));
    }

    @Override
    public Optional<BasketItemsRequest> save(BasketItemsRequest request) {
        if (request == null){
            return Optional.empty();
        }

        BasketItemsEntity result = new BasketItemsEntity(request);

        try {
            this.basketItemsRepo.save(result);
            log.info("Save Basket Items To database Success");
            return Optional.of(new BasketItemsRequest(result));
        } catch (Exception e){
            log.error("Save Basket Items to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<BasketItemsRequest> update(BasketItemsRequest request, String id) {
        BasketItemsEntity entity = this.basketItemsRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        entity.setId(id);

        try {
            this.basketItemsRepo.save(entity);
            log.info("Update Basket Items to database success");
            return Optional.of(new BasketItemsRequest(entity));
        } catch (Exception e){
            log.error("Update Basket Items to database failed, error:{}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<BasketItemsRequest> delete(String id) {
        BasketItemsEntity entity = this.basketItemsRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Basket Items With id :{}, not Found", id);
            return Optional.empty();
        }

        try {
            this.basketItemsRepo.delete(entity);
            log.info("Delete Basket Items from database success");
            return Optional.of(new BasketItemsRequest(entity));
        } catch (Exception e){
            log.info("Delete Basket Items From database failed, error:{}",e.getMessage());
            return Optional.empty();
        }


    }
}
