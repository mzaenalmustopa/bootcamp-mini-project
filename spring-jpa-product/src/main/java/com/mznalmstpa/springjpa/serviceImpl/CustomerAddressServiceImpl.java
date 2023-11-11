package com.mznalmstpa.springjpa.serviceImpl;

import com.mznalmstpa.springjpa.entity.CustomerAddressEntity;
import com.mznalmstpa.springjpa.model.CustomerAddressModel;
import com.mznalmstpa.springjpa.repository.CustomerAddressRepo;
import com.mznalmstpa.springjpa.service.CustomerAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final CustomerAddressRepo customerAddressRepo;

    public CustomerAddressServiceImpl(CustomerAddressRepo customerAddressRepo) {
        this.customerAddressRepo = customerAddressRepo;
    }

    @Override
    public List<CustomerAddressModel> gets() {
        List<CustomerAddressEntity> result = this.customerAddressRepo.findAll();
        if (result.isEmpty()){
            return Collections.emptyList();
        }

        return result.stream().map(CustomerAddressModel::new).collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerAddressModel> getById(Long id) {
        CustomerAddressEntity entity = this.customerAddressRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        CustomerAddressModel result = new CustomerAddressModel(entity);
        return Optional.of(result);
    }

    @Override
    public void save(CustomerAddressModel request) {
        // mengecek apakah customer dan address sudah terpakai atau belum
        if (this.customerAddressRepo.existsByCustomer(request.getCustomer())){
            log.warn("Save customer failed, error, account number with {} is exist", request);
            return;
        }

        // proses create object
        CustomerAddressEntity entity = new CustomerAddressEntity();
        // copy value property to entity
        BeanUtils.copyProperties(request, entity);
        // try save
        try {
            this.customerAddressRepo.save(entity);
            log.info("Save customer address Success");
        }catch (Exception e){
            log.warn("Save Customer Failed, error:{}",e.getMessage());
        }
    }

    @Override
    public void update(CustomerAddressModel request, Long id) {
        CustomerAddressEntity entity = this.customerAddressRepo.findById(id).orElse(null);
        if (entity == null){
            return;
        }

        // copy property
        BeanUtils.copyProperties(request, entity);
        entity.setId(id);
        // try save{
        try {
            this.customerAddressRepo.save(entity);
            log.info("Update Customer address success");
        }catch (Exception e){
            log.warn("Update customer address failed, error{}",e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        CustomerAddressEntity entity = this.customerAddressRepo.findById(id).orElse(null);
        if (entity == null){
            return;
        }

        try {
            this.customerAddressRepo.delete(entity);
            log.info("Delete Customer address success");
        }catch (Exception e){
            log.warn("Delete customer address failed, error{}",e.getMessage());
        }
    }
}
