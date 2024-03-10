package com.baby.babycareproductsshop.admin.order;

import com.baby.babycareproductsshop.admin.order.model.*;
import com.baby.babycareproductsshop.entity.order.OrderEntity;
import com.baby.babycareproductsshop.entity.refund.RefundEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminOrderQdlsRepository {
    long getCount(OrderCommonSearchFilterDto dto);
    List<OrderEntity> getOrderList(OrderCommonSearchFilterDto dto);
    List<OrderEntity> getUserOrderList(OrderCommonSearchFilterDto dto);
    List<OrderEntity> getOrderDetailsList(OrderCommonSearchFilterDto dto);
    List<OrderEntity> getOrderDeleteList(OrderCommonSearchFilterDto dto);
    List<RefundEntity> getOrderRefundList(OrderCommonSearchFilterDto dto);
    List<OrderEntity> getOrderAdminMemoList(OrderCommonSearchFilterDto dto);
    List<OrderEntity> getOrderDetails(int iorder);

    //------------------------------------------th
    List<AdminSelOrderSalesVo> selOrderSales(AdminSelOrderStatisticsDto dto);
    List<AdminSelTotalOrderCntVo> selTotalOrderCnt(AdminSelOrderStatisticsDto dto);
}
