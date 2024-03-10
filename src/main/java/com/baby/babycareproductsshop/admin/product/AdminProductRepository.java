package com.baby.babycareproductsshop.admin.product;

import com.baby.babycareproductsshop.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminProductRepository extends JpaRepository<ProductEntity, Long> {
}
