package com.bootcamp.northwind.service.serviceImpl;

import com.bootcamp.northwind.model.entity.CustomerEntity;
import com.bootcamp.northwind.model.request.CustomerRequest;
import com.bootcamp.northwind.repository.CustomerRepo;
import com.bootcamp.northwind.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        List<CustomerEntity> customer = this.customerRepo.findAll();
        if (customer.isEmpty()){
            return Collections.emptyList();
        }

        return customer.stream().map(CustomerRequest::new).collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerRequest> getById(Long id) {
        CustomerEntity customer = this.customerRepo.findById(id).orElse(null);
        if (customer == null){
            return Optional.empty();
        }

        return Optional.of(new CustomerRequest(customer));
    }

    @Override
    public Optional<CustomerRequest> save(CustomerRequest request) {
        if (request == null){
            return Optional.empty();
        }

        CustomerEntity entity = new CustomerEntity(request);
        BeanUtils.copyProperties(request, entity);

        try {
            this.customerRepo.save(entity);
            log.info("Save Customer to database success !");
            return Optional.of(new CustomerRequest(entity));
        } catch (Exception e){
            log.error("Save to database failed, error :{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CustomerRequest> update(CustomerRequest request, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<CustomerRequest> delete(Long id) {
        return Optional.empty();
    }
}
