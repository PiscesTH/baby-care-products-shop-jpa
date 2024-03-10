package com.baby.babycareproductsshop.admin.category;

import com.baby.babycareproductsshop.entity.product.ProductMainCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminMainCategoryRepository extends JpaRepository<ProductMainCategoryEntity, Long> {

}
