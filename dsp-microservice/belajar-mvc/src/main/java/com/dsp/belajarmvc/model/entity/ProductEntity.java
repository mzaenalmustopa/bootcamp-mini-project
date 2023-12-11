package com.dsp.belajarmvc.model.entity;

import com.dsp.belajarmvc.model.request.ProductRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_code", length = 20, unique = true, nullable = false)
    private String code;

    @Column(name = "product_name", length = 150, nullable = false)
    private String name;

    @Column(name = "product_price")
    private Long price;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "expired_date")
    private Date expireDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public ProductEntity(ProductRequest request, CategoryEntity category) {
        BeanUtils.copyProperties(request, this);
        this.category = category;
    }
}
