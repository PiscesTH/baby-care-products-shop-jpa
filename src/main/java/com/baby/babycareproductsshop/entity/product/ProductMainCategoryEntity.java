package com.baby.babycareproductsshop.entity.product;

import com.baby.babycareproductsshop.entity.CreatedAtEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_main_category")
public class ProductMainCategoryEntity extends CreatedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10, columnDefinition = "BIGINT UNSIGNED")
    private Long imain;

    @Column(length = 20, name = "main_category", nullable = false)
    private String mainCategory;
}
