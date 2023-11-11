package com.mznalmstpa.springjpa.serviceImpl;

import com.mznalmstpa.springjpa.entity.CustomerAddressEntity;
import com.mznalmstpa.springjpa.entity.CustomerEntity;
import com.mznalmstpa.springjpa.model.CustomerAddressModel;
import com.mznalmstpa.springjpa.model.CustomerModel;
import com.mznalmstpa.springjpa.repository.CustomerAddressRepo;
import com.mznalmstpa.springjpa.repository.CustomerRepo;
import com.mznalmstpa.springjpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerAddressRepo addressRepo;

    @Override
    public List<CustomerModel> gets() {
        List<CustomerEntity> result = this.customerRepo.findAll();
        if (result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(CustomerModel::new).collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerModel> getById(Long id) {
        CustomerEntity entity = this.customerRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        CustomerModel result = new CustomerModel(entity);
        return Optional.of(result);
    }

    @Override
    public void save(CustomerModel request) {
        // mengecek apakah account no sudah terpakai atau belum
        if (this.customerRepo.existsByAccountNo(request.getAccountNo())){
            log.warn("Save customer failed, error, account number with {} is exist", request.getAccountNo());
            return;
        }
        //proses create object
        CustomerEntity entity = new CustomerEntity();
        // copy value property to entity
        BeanUtils.copyProperties(request, entity);
        // try save
        try {
            this.customerRepo.save(entity);
            log.info("Save Customer Success");
        }catch (Exception e){
            log.warn("Save Customer Failed, error:{}",e.getMessage());
        }
    }

    @Override
    public void saveAddress(CustomerAddressModel request) {
        if (request.getCustomerId() == 0L){
            return;
        }

        CustomerEntity customer = this.customerRepo.findById(request.getCustomerId()).orElse(null);
        if (customer == null){
            return;
        }

        CustomerAddressEntity entity = new CustomerAddressEntity();
        BeanUtils.copyProperties(request, entity);
        entity.setCustomer(customer);
        try {
            this.addressRepo.save(entity);
            log.info("Save Customer Success..!");
        }catch (Exception e){
            log.error("Save Customer Failed , error:{}",e.getMessage());
        }

    }

    @Override
    public void update(CustomerModel request, Long id) {
        CustomerEntity entity = this.customerRepo.findById(id).orElse(null);
        if (entity == null){
            return;
        }

        // copy property
        BeanUtils.copyProperties(request, entity);
        entity.setId(id);
        // try save
        try {
            this.customerRepo.save(entity);
            log.info("Update Customer Success");
        }catch (Exception e){
            log.warn("Update customer failed, error:{}",e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        CustomerEntity entity = this.customerRepo.findById(id).orElse(null);
        if (entity == null){
            return;
        }

        try {
            this.customerRepo.delete(entity);
            log.info("Delete Customer Success");
        }catch (Exception e){
            log.warn("Delete Customer failed, error:{}",e.getMessage());
        }
    }
}
