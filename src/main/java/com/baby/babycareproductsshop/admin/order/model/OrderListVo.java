package com.baby.babycareproductsshop.admin.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrderListVo {
    @JsonIgnore
    private int processState;
    @Schema(title = "주문번호", description = "")
    private int iorder;
    @Schema(title = "주문일시", description = "")
    private String orderedAt;
    @Schema(title = "주문상품 정보", description = "")
    private List<OrderProductVo> products;
    @Schema(title = "주문자", description = "")
    private String ordered;
    @Schema(title = "수령자", description = "")
    private String recipient;
    @Schema(title = "총주문액", description = "")
    private int totalAmount;
    @Schema(title = "결제수단", description = "")
    private int payCategory;
    @Schema(title = "반품 신청", description = "0 - '반품 신청' 버튼 출력 X<br>1 - '반품 신청' 버튼 출력 O")
    private int refundFl;
    @Schema(title = "리스트 총 개수", description = "")
    private long totalCount;
}
