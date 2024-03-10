package com.baby.babycareproductsshop.admin.order;

import com.baby.babycareproductsshop.entity.order.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminOrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Long>, AdminOrderDetailsQdlsRepository {
}