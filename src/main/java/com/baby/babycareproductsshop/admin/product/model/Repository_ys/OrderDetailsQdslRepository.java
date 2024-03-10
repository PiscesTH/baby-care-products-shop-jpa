package com.baby.babycareproductsshop.admin.product.model.Repository_ys;

import com.baby.babycareproductsshop.admin.product.model.AdminRefundReturnDto;
import com.baby.babycareproductsshop.entity.order.OrderDetailsEntity;
import com.baby.babycareproductsshop.entity.order.OrderEntity;

import java.util.List;

public interface OrderDetailsQdslRepository {
    List<OrderDetailsEntity> refundReturnSelVo(AdminRefundReturnDto dto);
}
