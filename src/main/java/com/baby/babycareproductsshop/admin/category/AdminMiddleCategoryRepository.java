package com.baby.babycareproductsshop.admin.category;

import com.baby.babycareproductsshop.entity.product.ProductMiddleCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminMiddleCategoryRepository extends JpaRepository<ProductMiddleCategoryEntity, Long> {
    List<ProductMiddleCategoryEntity> getByProductMainCategory_Imain(long imain);

    @Query("SELECT MAX(m.imiddle) FROM ProductMiddleCategoryEntity as m WHERE m.productMainCategory.imain = :imain")
    int getMiddleCategoryMaxNumber(long imain);
}