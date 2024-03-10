package com.baby.babycareproductsshop.admin.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "[Get]api/admin/user response",description = "관리자페이지에서 회원 정보 조회 응답 데이터")
public class AdminSelAllUserVo {
    @Schema(title = "유저pk")
    private Long iuser;
    @Schema(title = "유저 이름")
    private String nm;
    @Schema(title = "유저 이메일")
    private String email;
    @Schema(title = "유저 전화번호")
    private String phoneNumber;
    @Schema(title = "회원가입일")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private LocalDateTime registeredAt;
    @Schema(title = "회원탈퇴일")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private LocalDateTime unregisteredAt;
    @Schema(title = "총 데이터 수")
    private long totalCnt;
}
