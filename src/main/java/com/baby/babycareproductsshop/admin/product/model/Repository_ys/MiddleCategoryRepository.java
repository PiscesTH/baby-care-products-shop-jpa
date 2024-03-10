package com.baby.babycareproductsshop.admin.product.model.Repository_ys;


import com.baby.babycareproductsshop.entity.product.ProductMiddleCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiddleCategoryRepository extends JpaRepository<ProductMiddleCategoryEntity,Long> {
    ProductMiddleCategoryEntity findByImiddleAndProductMainCategory_Imain(Long imiddle,Long imain);
}
