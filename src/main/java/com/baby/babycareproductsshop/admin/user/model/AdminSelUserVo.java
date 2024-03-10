package com.baby.babycareproductsshop.admin.user.model;

import com.baby.babycareproductsshop.user.model.UserChildDto;
import com.baby.babycareproductsshop.user.model.UserInsAddressDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Schema(title = "[Get]api/admin/user/{iuser} response", description = "회원 정보 수정 시 기존 데이터 호출 응답값")
public class AdminSelUserVo {
    @Schema(title = "이름")
    private String nm;
    @Schema(title = "회원가입일")
    private LocalDateTime registeredAt;
    @Schema(title = "아이디")
    private String uid;
    @Schema(title = "전화번호")
    private String phoneNumber;
    @Schema(title = "이메일")
    private String email;
    @Schema(title = "주소 리스트")
    private List<UserInsAddressDto> addresses;
    @Schema(title = "자녀 리스트")
    private List<UserChildDto> children;
    @Schema(title = "관리자 메모")
    private String adminMemo;
}