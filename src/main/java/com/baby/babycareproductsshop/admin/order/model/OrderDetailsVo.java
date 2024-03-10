package com.baby.babycareproductsshop.admin.order.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrderDetailsVo {
    @Schema(title = "주문상품 정보", description = "")
    private List<OrderProductVo> products;
    @Schema(title = "상품금액", description = "")
    private int productAmount;
    @Schema(title = "취소금액", description = "")
    private int deleteAmount;
    @Schema(title = "반품금액", description = "")
    private int refundAmount;
    @Schema(title = "결제금액", description = "")
    private int totalAmount;
    @Schema(title = "주문번호", description = "")
    private int iorder;
    @Schema(title = "주문일시", description = "")
    private String orderedAt;
    @Schema(title = "결제수단", description = "")
    private int payCategory;
    @Schema(title = "결제상태", description = "")
    private int processState;
    @Schema(title = "주문자명", description = "")
    private String ordered;
    @Schema(title = "주문자이메일", description = "")
    private String orderedEmail;
    @Schema(title = "주문자연락처", description = "")
    private String orderedPhoneNumber;
    @Schema(title = "수령자명", description = "")
    private String recipient;
    @Schema(title = "수령자연락처", description = "")
    private String recipientPhoneNumber;
    @Schema(title = "배송지", description = "")
    private String address;
    @Schema(title = "관리자메모", description = "")
    private String adminMemo;
}
