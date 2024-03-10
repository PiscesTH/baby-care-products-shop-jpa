package com.baby.babycareproductsshop.entity.product;

import com.baby.babycareproductsshop.entity.CreatedAtEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_product_pics")
@Data
public class ProductPicEntity extends CreatedAtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iproduct_pics")
    private Long iproduct;

    @ManyToOne
    @JoinColumn(name = "iproduct", nullable = false)
    private ProductEntity productEntity;

    @Column(name = "product_pic", length = 1000, nullable = false)
    private String productPic;
}
