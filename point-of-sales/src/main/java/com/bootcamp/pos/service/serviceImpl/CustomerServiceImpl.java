package com.bootcamp.pos.service.serviceImpl;

import com.bootcamp.pos.model.entity.CustomerEntity;
import com.bootcamp.pos.model.request.CustomerRequest;
import com.bootcamp.pos.repository.CustomerRepo;
import com.bootcamp.pos.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    @Override
    public List<CustomerRequest> getAll() {
        return this.customerRepo.findAll().stream().map(CustomerRequest::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional <CustomerRequest> getById(String id) {
        CustomerEntity result = this.customerRepo.findById(id).orElse(null);
        if (result == null){
            return Optional.empty();
        }

        return Optional.of(new CustomerRequest(result));
    }

    @Override
    public Optional<CustomerRequest> save(CustomerRequest request) {
        if (request == null){
            return Optional.empty();
        }

        CustomerEntity result = new CustomerEntity(request);

        try {
            this.customerRepo.save(result);
            log.info("Save Customer To database Success");
            return Optional.of(new CustomerRequest(result));
        } catch (Exception e){
            log.error("Save Customer to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CustomerRequest> update(CustomerRequest request, String id) {
        CustomerEntity entity = this.customerRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        entity.setId(id);

        try {
            this.customerRepo.save(entity);
            log.info("Update Customer to database success");
            return Optional.of(new CustomerRequest(entity));
        } catch (Exception e){
            log.error("Update Customer to database failed, error:{}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CustomerRequest> delete(String id) {
        CustomerEntity entity = this.customerRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Customer With id :{}, not Found", id);
            return Optional.empty();
        }

        try {
            this.customerRepo.delete(entity);
            log.info("Delete Customer from database success");
            return Optional.of(new CustomerRequest(entity));
        } catch (Exception e){
            log.info("Delete Customer From database failed, error:{}",e.getMessage());
            return Optional.empty();
        }


    }
}
