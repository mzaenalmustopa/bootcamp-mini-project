package com.bootcamp.pos.model.request;

import com.bootcamp.pos.model.entity.PaymentMethodEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodRequest {

    private String id;
    private String paymentMethodCode;
    private String paymentDesc;
    private String creditCard;

    public PaymentMethodRequest(PaymentMethodEntity result) {
        BeanUtils.copyProperties(result, this);
    }
}
