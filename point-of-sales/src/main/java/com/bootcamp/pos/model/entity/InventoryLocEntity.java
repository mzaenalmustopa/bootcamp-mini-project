package com.bootcamp.pos.model.entity;

import com.bootcamp.pos.model.request.InventoryLocRequest;
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
@Table(name = "tbl_inventory_loc")
public class InventoryLocEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "products_id")
    private String productsId;

    @Column(name = "loc_address_id")
    private String locationAddressId;

    @Column(name = "quantity_stock")
    private Double quantityStock;

    @Column(name = "order_level")
    private Double orderLevel;

    @Column(name = "order_quantity")
    private Double orderQuantity;

    @Column(name = "average_month")
    private String averageMonth;

    @Column(name = "inventory_details")
    private String inventoryDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "products_id", insertable = false, updatable = false)
    private ProductsEntity products;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loc_address_id", insertable = false, updatable = false)
    private AddressEntity address;

    public InventoryLocEntity(InventoryLocRequest request) {
        BeanUtils.copyProperties(request, this);
        this.id = UUID.randomUUID().toString();
    }
}
