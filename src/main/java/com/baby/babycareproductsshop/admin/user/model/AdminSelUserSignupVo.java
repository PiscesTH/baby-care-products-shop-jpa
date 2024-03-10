package com.baby.babycareproductsshop.admin.user.model;

import com.baby.babycareproductsshop.admin.order.model.StatisticsVo;
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
@Schema(title = "[Get]api/admin/user/signup response", description = "회원가입통계 조회 응답 데이터")
public class AdminSelUserSignupVo implements StatisticsVo, Comparable<AdminSelUserSignupVo> {
    @Schema(title = "날짜")
    private String date;
    @Schema(title = "회원가입 수")
    private int registerCnt;
    @Schema(title = "회원가입 비율")
    private String registerRate;
    @Schema(title = "총 회원가입 수")
    private int totalRegisterCnt;
    @JsonIgnore
    private LocalDateTime createdAt;

    public AdminSelUserSignupVo(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(AdminSelUserSignupVo o) {
        return Integer.compare(getLastValue(this.date), getLastValue(o.date)) * -1;
    }
}
