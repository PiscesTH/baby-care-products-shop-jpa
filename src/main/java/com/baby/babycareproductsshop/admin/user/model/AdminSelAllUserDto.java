package com.baby.babycareproductsshop.admin.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(title = "[Get]api/admin/user request",description = "관리자페이지에서 회원 정보 조회 요청 데이터")
public class AdminSelAllUserDto {
    @Schema(title = "회원 검색 기준 구분값", description = """
            unregisterFl = 0 : 탈퇴하지 않은 회원 정보 조회<br>
            unregisterFl = 1 : 탈퇴한 회원 정보 조회
            """)
    private long unregisteredFl;
    @Schema(title = "검색어")
    private String keyword;
    @Schema(title = "검색 종류", description = """
            1 : 아이디 검색<br>
            2 : 이름 검색
            """)
    private long keywordType;
    @Schema(title = "기간 검색 시작 기간", defaultValue = "null")
    private LocalDate before;
    @Schema(title = "기간 검색 종료 기간", defaultValue = "null")
    private LocalDate after;
    @Schema(title = "전화번호 검색")
    private String phoneNumber;
}
