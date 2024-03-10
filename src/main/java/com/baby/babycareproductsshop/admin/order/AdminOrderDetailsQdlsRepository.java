package com.baby.babycareproductsshop.admin.order;

import com.baby.babycareproductsshop.entity.order.OrderDetailsEntity;

import java.util.List;

public interface AdminOrderDetailsQdlsRepository {
    List<OrderDetailsEntity> findAll(long idetails);
}
