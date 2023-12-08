package com.bootcamp.pos.model.entity;

import com.bootcamp.pos.model.request.CustomerRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_customer")
public class CustomerEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "pay_method_code_id")
    private String paymentMethodCodeId;

    @Column(name = "customer_name", length = 200)
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_email", unique = true)
    private String customerEmail;

    @Column(name = "date_customer")
    private LocalDate dateCustomer;

    @Column(name = "payment_details")
    private String paymentDetails;

    @Column(name = "other_details")
    private String otherCustomerDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pay_method_code_id", insertable = false, updatable = false)
    private PaymentMethodEntity paymentMethod;

    public CustomerEntity(CustomerRequest request) {
        BeanUtils.copyProperties(request, this);
        this.id = UUID.randomUUID().toString();
    }
}
