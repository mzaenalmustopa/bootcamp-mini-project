package com.bootcamp.pos.model.entity;

import com.bootcamp.pos.model.request.BasketItemsRequest;
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
@Table(name = "tbl_basket_items")
public class BasketItemsEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "basket_date_id")
    private String basketDateTimeId;

    @Column(name = "products_id")
    private String productsId;

    @Column(name = "quantity", length = 60)
    private Double quantity;

    @Column(name = "cost")
    private Double cost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "basket_date_id", insertable = false, updatable = false)
    private ShoppingBasketEntity shoppingBasket;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "products_id", insertable = false, updatable = false)
    private ProductsEntity products;

    public BasketItemsEntity(BasketItemsRequest request) {
        BeanUtils.copyProperties(request, this);
        this.id = UUID.randomUUID().toString();
    }
}
