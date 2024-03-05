package com.baby.babycareproductsshop.admin.product.model.Repository_ys;

import com.baby.babycareproductsshop.entity.product.ProductPicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductPicRepository extends JpaRepository<ProductPicEntity,Long> {



    List<ProductPicEntity> findByIproduct(Long iproduct);
}
