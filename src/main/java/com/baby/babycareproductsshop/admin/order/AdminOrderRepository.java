package com.baby.babycareproductsshop.admin.order;

import com.baby.babycareproductsshop.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AdminOrderRepository extends JpaRepository<OrderEntity, Long>, AdminOrderQdlsRepository {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE t_order SET process_state = 4 where delivered_at >= DATE_ADD(NOW(), INTERVAL -3 DAY) AND process_state = 3")
    void deliveredAutoChange();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE t_order SET process_state = 5 WHERE created_at >= DATE_ADD(NOW(), INTERVAL -3 DAY) AND process_state = 1")
    void orderCancelAutoChange();
}