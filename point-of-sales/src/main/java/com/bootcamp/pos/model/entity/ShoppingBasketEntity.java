package com.bootcamp.pos.model.entity;

import com.bootcamp.pos.model.request.ShoppingBasketRequest;
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
@Table(name = "tbl_shopping_basket")
public class ShoppingBasketEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "basket_date")
    private String basketDateTime;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "other_details")
    private String otherDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerEntity customer;

    public ShoppingBasketEntity(ShoppingBasketRequest request) {
        BeanUtils.copyProperties(request, this);
        this.id = UUID.randomUUID().toString();
    }
}
