package com.baby.babycareproductsshop.admin.product.model.Repository_ys;


import com.baby.babycareproductsshop.admin.product.model.AdminRefundReturnDto;
import com.baby.babycareproductsshop.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderTotalRepository extends JpaRepository<OrderEntity,Long>, OrderTotalQdslRepository {



}
