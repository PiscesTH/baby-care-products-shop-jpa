package com.baby.babycareproductsshop.admin.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static com.baby.babycareproductsshop.common.Const.*;

@Data
@Schema(title = "[Patch]api/admin/user/{iuser} request", description = "관리자에 의한 회원 정보 수정 필요 데이터")
public class AdminUpdUserDto {
    @Schema(description = "유저 비밀번호", example = "특수문자는 @$!%*?&#~_-를 사용할 수 있습니다.")
    @NotNull(message = PASSWORD_IS_BLANK)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&#~_-])[A-Za-z\\d@$!%*?&#.~_-]{8,16}|()$",
            message = NOT_ALLOWED_PASSWORD)
    private String upw;
    @Schema(title = "관리자 메모")
    private String adminMemo;
}
