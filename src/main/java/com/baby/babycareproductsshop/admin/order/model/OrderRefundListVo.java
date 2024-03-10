package com.baby.babycareproductsshop.admin.order.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderRefundListVo {
    @Schema(title = "주문번호", description = "")
    private int iorder;
    @Schema(title = "주문일시", description = "")
    private String orderedAt;
    @Schema(title = "이미지", description = "")
    private String repPic;
    @Schema(title = "주문상품 정보", description = "")
    private String productNm;
    @Schema(title = "수량", description = "")
    private int cnt;
    @Schema(title = "상품 금액", description = "")
    private int productPrice;
    @Schema(title = "처리 상태", description = "")
    private int processState;
    @Schema(title = "반품일시", description = "")
    private String refundedAt;
    @Schema(title = "주문자", description = "")
    private String ordered;
    @Schema(title = "결제수단", description = "")
    private String paymentOption;
    @Schema(title = "리스트 총 개수", description = "")
    private long totalCount;
}
