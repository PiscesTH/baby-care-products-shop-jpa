package com.baby.babycareproductsshop.entity.product;

import com.baby.babycareproductsshop.entity.CreatedAtEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_middle_category", uniqueConstraints={
        @UniqueConstraint(columnNames = {"imain", "imiddle"})
})
@Data
public class ProductMiddleCategoryEntity extends CreatedAtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_key", columnDefinition = "BIGINT UNSIGNED")
    private Long candidateKey;

    @ManyToOne
    @JoinColumn(name = "imain", columnDefinition = "BIGINT UNSIGNED")
    private ProductMainCategoryEntity productMainCategory;

    @Column(name = "imiddle", columnDefinition = "BIGINT UNSIGNED")
    private Long imiddle;

    @Column(name = "middle_category", nullable = false)
    private String middleCategory;
}
