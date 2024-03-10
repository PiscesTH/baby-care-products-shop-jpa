package com.baby.babycareproductsshop.admin.user;

import com.baby.babycareproductsshop.admin.user.model.*;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.response.ApiResponse;
import com.baby.babycareproductsshop.user.model.UserSignInDto;
import com.baby.babycareproductsshop.user.model.UserSignInVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name = "관리자 기능 - 회원 관리 API")
public class AdminUserController {
    private final AdminUserService service;

    @Operation(summary = "관리자 로그인")
    @PostMapping
    public ApiResponse<UserSignInVo> postSigninAdmin(HttpServletResponse res, @RequestBody UserSignInDto dto) {
        return service.postSigninAdmin(res, dto);
    }

    @Operation(summary = "회원 정보 전체 조회", description = """
            unregisterFl = 0 : 탈퇴하지 않은 회원 정보 조회<br>
            unregisterFl = 1 : 탈퇴한 회원 정보 조회<br>
            <br>
            page : 1부터 시작<br>
            size : 한 페이지에 들어갈 데이터 개수<br>
            sort : 지금은 작동 안하니까 그냥 그대로 두시면 됩니다.
            """)
    @GetMapping("/user")
    public ApiResponse<List<AdminSelAllUserVo>> getUserList(AdminSelAllUserDto dto, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return service.getUserList(dto, pageable);
    }

    @Operation(summary = "회원 정보 수정 시 기존 데이터 호출")
    @GetMapping("/user/{iuser}")
    public ApiResponse<AdminSelUserVo> getUserInfo(@PathVariable long iuser) {
        return service.getUserInfo(iuser);
    }

    @Operation(summary = "관리자에 의한 회원 정보 수정")
    @PatchMapping("/user/{iuser}")
    public ApiResponse<?> patchUserInfo(@PathVariable long iuser, @RequestBody @Valid AdminUpdUserDto dto) {
        return service.patchUserInfo(iuser, dto);
    }

    @Operation(summary = "가입통계분석 조회", description = """
            year & month = 0 : 연도별<br>
            year != 0 & month = 0 : 월별<br>
            year & month  != 0: 일별
            """)
    @GetMapping("/user/signup")
    public ApiResponse<List<AdminSelUserSignupVo>> getUserSignupStatistics(AdminSelUserSignupDto dto) {
        return service.getUserSignupStatistics(dto);
    }

    @Operation(summary = "관리자에 의한 회원 탈퇴 & 복구", description = """
            result = 0 : 회원 탈퇴X<br>
            result = 1 : 회원 탈퇴O
            """)
    @DeleteMapping("/user/{iuser}")
    public ApiResponse<ResVo> changeRegistrationStatus(@PathVariable long iuser) {
        return service.changeRegistrationStatus(iuser);
    }
}
