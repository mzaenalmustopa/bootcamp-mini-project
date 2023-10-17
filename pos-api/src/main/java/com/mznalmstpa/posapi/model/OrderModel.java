package com.mznalmstpa.posapi.model;

import com.mznalmstpa.posapi.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    private String invoiceNo;
    private Date orderDate;
    private Double grandTotal;
    private  OrderShipModel ship;
    private CustomerModel customer;
    private List<OrderDetailModel> orderDetail;

    public OrderModel(OrderEntity entity) {
        this.invoiceNo = entity.getInvoiceNo();
        this.orderDate = entity.getOrderDate();
        this.grandTotal = entity.getGrandTotal();
        // memanggil cosntructor OrdershipModel (entity)
        this.ship = new OrderShipModel(entity);

        if (entity.getCustomer() != null){
            // memanggil cosntructor customerModel (Entity)
            this.customer = new CustomerModel(entity.getCustomer());
        }

        if (!entity.getOrderDetail().isEmpty()){
            this.orderDetail = entity.getOrderDetail()
                    .stream().map(OrderDetailModel::new).collect(Collectors.toList());
        }
    }
}
