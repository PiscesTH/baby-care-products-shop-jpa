package com.baby.babycareproductsshop.admin.product.model.Repository_ys;

import com.baby.babycareproductsshop.admin.product.model.AdminRefundReturnDto;
import com.baby.babycareproductsshop.entity.order.OrderEntity;

import java.util.List;

public interface OrderTotalQdslRepository {
    List<OrderEntity> refundReturnSelVo(AdminRefundReturnDto dto);
}
