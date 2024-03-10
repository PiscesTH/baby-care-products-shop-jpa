package com.baby.babycareproductsshop.admin.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@Slf4j
@NoArgsConstructor
@Schema(title = "매출 통계 response",description = "매출 통계 조회 응답 데이터")
public class AdminSelOrderSalesVo implements StatisticsVo, Comparable<AdminSelOrderSalesVo>{
    @Schema(title = "총 매출")
    private int totalSales;
    @JsonIgnore
    private LocalDateTime createdAt;
    @JsonIgnore
    private int processState;
    @JsonIgnore
    private int deleteFl;

    @Schema(title = "순수익")
    private int earnings;
    @Schema(title = "총 판매한 제품 원가")
    private int costPrice;
    @Schema(title = "날짜")
    private String date;

    public AdminSelOrderSalesVo(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(AdminSelOrderSalesVo o) {
        return Integer.compare(getLastValue(o.date), getLastValue(this.date));
    }

}
