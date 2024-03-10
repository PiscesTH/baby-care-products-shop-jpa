package com.baby.babycareproductsshop.admin.product.model;

import com.baby.babycareproductsshop.admin.order.model.StatisticsVo;
import com.baby.babycareproductsshop.admin.user.model.AdminSelUserSignupVo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminRefundReturnSelVo implements Statist , Comparable<AdminRefundReturnSelVo>{

    @Schema(title = "날짜")
    private String date;
    @Schema(title = "취소 수")
    private int registerCnt;
    @Schema(title = " 비율")
    private String registerRate;
    @Schema(title = "전체 수")
    private int totalRegisterCnt;
    @JsonIgnore
    private LocalDateTime updatedAt;
    public AdminRefundReturnSelVo(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(AdminRefundReturnSelVo o) {
        return Integer.compare(getValue(this.date), getValue(o.date)) * -1;
    }


}
