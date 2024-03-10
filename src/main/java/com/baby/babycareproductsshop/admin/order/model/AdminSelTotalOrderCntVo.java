package com.baby.babycareproductsshop.admin.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Schema(title = "주문 통계 response", description = "주문 통계 조회 응답 데이터")
public class AdminSelTotalOrderCntVo implements StatisticsVo, Comparable<AdminSelTotalOrderCntVo>{
    @Schema(title = "순수 주문량")
    private int totalOrderCnt;
    @JsonIgnore
    private LocalDateTime createdAt;
    @Schema(title = "주문 취소/환불 건")
    private int recallCnt;
    @Schema(title = "총 주문수")
    private int netOrderCnt;
    @Schema(title = "날짜")
    private String date;

    public AdminSelTotalOrderCntVo(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(AdminSelTotalOrderCntVo o) {
        return Integer.compare(getLastValue(this.date), getLastValue(o.date)) * -1;
    }


}
