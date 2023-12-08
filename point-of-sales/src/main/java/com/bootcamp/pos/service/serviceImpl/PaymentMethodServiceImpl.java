package com.bootcamp.pos.service.serviceImpl;

import com.bootcamp.pos.model.entity.PaymentMethodEntity;
import com.bootcamp.pos.model.request.PaymentMethodRequest;
import com.bootcamp.pos.repository.PaymentMethodRepo;
import com.bootcamp.pos.service.AddressService;
import com.bootcamp.pos.service.PaymentMethodService;
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
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepo paymentMethodRepo;
    @Override
    public List<PaymentMethodRequest> getAll() {
        return this.paymentMethodRepo.findAll().stream().map(PaymentMethodRequest::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional <PaymentMethodRequest> getById(String id) {
        PaymentMethodEntity result = this.paymentMethodRepo.findById(id).orElse(null);
        if (result == null){
            return Optional.empty();
        }

        return Optional.of(new PaymentMethodRequest(result));
    }

    @Override
    public Optional<PaymentMethodRequest> save(PaymentMethodRequest request) {
        if (request == null){
            return Optional.empty();
        }

        PaymentMethodEntity result = new PaymentMethodEntity(request);

        try {
            this.paymentMethodRepo.save(result);
            log.info("Save Payment Method To database Success");
            return Optional.of(new PaymentMethodRequest(result));
        } catch (Exception e){
            log.error("Save Payment Method to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<PaymentMethodRequest> update(PaymentMethodRequest request, String id) {
        PaymentMethodEntity entity = this.paymentMethodRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        entity.setId(id);

        try {
            this.paymentMethodRepo.save(entity);
            log.info("Update Payment Method to database success");
            return Optional.of(new PaymentMethodRequest(entity));
        } catch (Exception e){
            log.error("Update Payment Method to database failed, error:{}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<PaymentMethodRequest> delete(String id) {
        PaymentMethodEntity entity = this.paymentMethodRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Payment Method With id :{}, not Found", id);
            return Optional.empty();
        }

        try {
            this.paymentMethodRepo.delete(entity);
            log.info("Delete Data Payment Method from database success");
            return Optional.of(new PaymentMethodRequest(entity));
        } catch (Exception e){
            log.info("Delete Data Payment Method From database failed, error:{}",e.getMessage());
            return Optional.empty();
        }


    }
}
