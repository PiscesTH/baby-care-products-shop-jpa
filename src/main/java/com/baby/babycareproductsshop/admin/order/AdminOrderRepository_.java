package com.baby.babycareproductsshop.admin.order;

import com.baby.babycareproductsshop.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminOrderRepository_ extends JpaRepository<OrderEntity, Long>, AdminOrderQdlsRepository_ {
}
