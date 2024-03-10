package com.baby.babycareproductsshop.admin.order.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrderMemoUpdDto {
    @Schema(title = "주문번호", description = "")
    private long iorder;
    @Schema(title = "관리자 메모", description = "")
    private String adminMemo;
}
