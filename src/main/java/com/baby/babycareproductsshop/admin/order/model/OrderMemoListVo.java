package com.baby.babycareproductsshop.admin.order.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderMemoListVo {
    @Schema(title = "주문번호", description = "")
    private int iorder;
    @Schema(title = "주문일시", description = "")
    private String orderedAt;
    @Schema(title = "주문자", description = "")
    private String ordered;
    @Schema(title = "처리상태", description = "")
    private int processState;
    @Schema(title = "메모", description = "")
    private String memo;
    @Schema(title = "리스트 총 개수", description = "")
    private long totalCount;
}
