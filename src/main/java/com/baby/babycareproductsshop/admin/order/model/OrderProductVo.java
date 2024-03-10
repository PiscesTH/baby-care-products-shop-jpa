package com.baby.babycareproductsshop.admin.order.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderProductVo {
    @Schema(title = "상품 PK", description = "이미지 경로 호출 시 사용")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private long iproduct;
    @Schema(title = "이미지", description = "")
    private String repPic;
    @Schema(title = "주문 상품", description = "")
    private String productNm;
    @Schema(title = "수량", description = "")
    private int cnt;
    @Schema(title = "처리 상태", description = "")
    private int processState;
    @JsonInclude(JsonInclude.Include.NON_EMPTY) // 수정 2024-02-26 20:28
    private int amount;
    @JsonInclude
    private int refundFl;
}
