package com.bootcamp.pos.model.entity;

import com.bootcamp.pos.model.request.PaymentMethodRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_refpayment_method")
public class PaymentMethodEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "pay_meth_code")
    private String paymentMethodCode;

    @Column(name = "payment_method_desc")
    private String paymentDesc;

    @Column(name = "credit_card")
    private String creditCard;

    public PaymentMethodEntity(PaymentMethodRequest request) {
        BeanUtils.copyProperties(request, this);
        this.id = UUID.randomUUID().toString();
    }
}
