package com.baby.babycareproductsshop.admin.order.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "[Get]api/admin/order/{type} request",description = "매출 및 주문 통계 조회 요청 데이터")
public class AdminSelOrderStatisticsDto {
    @Schema(title = "연도")
    private int year;
    @Schema(title = "월")
    private int month;

}
