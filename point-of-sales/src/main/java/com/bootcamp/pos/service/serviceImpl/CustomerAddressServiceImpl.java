package com.bootcamp.pos.service.serviceImpl;

import com.bootcamp.pos.model.entity.CustomerAddressEntity;
import com.bootcamp.pos.model.request.CustomerAddressRequest;
import com.bootcamp.pos.repository.AddressRepo;
import com.bootcamp.pos.repository.CustomerAddressRepo;
import com.bootcamp.pos.repository.CustomerRepo;
import com.bootcamp.pos.service.CustomerAddressService;
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
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final CustomerAddressRepo customerAddressRepo;
    @Override
    public List<CustomerAddressRequest> getAll() {
        return this.customerAddressRepo.findAll().stream().map(CustomerAddressRequest::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional <CustomerAddressRequest> getById(String id) {
        CustomerAddressEntity result = this.customerAddressRepo.findById(id).orElse(null);
        if (result == null){
            return Optional.empty();
        }

        return Optional.of(new CustomerAddressRequest(result));
    }

    @Override
    public Optional<CustomerAddressRequest> save(CustomerAddressRequest request) {
        if (request == null){
            return Optional.empty();
        }

        CustomerAddressEntity result = new CustomerAddressEntity(request);

        try {
            this.customerAddressRepo.save(result);
            log.info("Save Customer Address To database Success");
            return Optional.of(new CustomerAddressRequest(result));
        } catch (Exception e){
            log.error("Save Customer Address to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CustomerAddressRequest> update(CustomerAddressRequest request, String id) {
        CustomerAddressEntity entity = this.customerAddressRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        entity.setId(id);

        try {
            this.customerAddressRepo.save(entity);
            log.info("Update Customer Address to database success");
            return Optional.of(new CustomerAddressRequest(entity));
        } catch (Exception e){
            log.error("Update Customer Address to database failed, error:{}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CustomerAddressRequest> delete(String id) {
        CustomerAddressEntity entity = this.customerAddressRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Customer Address With id :{}, not Found", id);
            return Optional.empty();
        }

        try {
            this.customerAddressRepo.delete(entity);
            log.info("Delete Customer Address from database success");
            return Optional.of(new CustomerAddressRequest(entity));
        } catch (Exception e){
            log.info("Delete Customer Address From database failed, error:{}",e.getMessage());
            return Optional.empty();
        }


    }
}
