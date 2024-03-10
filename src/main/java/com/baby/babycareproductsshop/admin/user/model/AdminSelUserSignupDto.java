package com.baby.babycareproductsshop.admin.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "[Get]api/admin/user/signup request",description = "관리자페이지에서 가입통계분석 조회 요청 데이터")
public class AdminSelUserSignupDto {
    @Schema(title = "연도")
    private int year;
    @Schema(title = "월")
    private int month;
}
